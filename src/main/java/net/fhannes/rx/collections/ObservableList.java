package net.fhannes.rx.collections;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import net.fhannes.rx.collections.util.Indexed;

import java.util.*;

/**
 * This class is a reactive list. It is a wrapper around a standard {@link List<E>} object, providing various Observable
 * objects which emit values when operations are performed on the list.
 *
 * @param <E> The type of elements stored in the list.
 */
class ObservableList<E> implements List<E> {

    private List<E> list;
    private PublishSubject<Indexed<E>> added = PublishSubject.create();
    private PublishSubject<Indexed<E>> removed = PublishSubject.create();
    private PublishSubject<Indexed<E>> updated = PublishSubject.create();
    private PublishSubject<Indexed<E>> updatedChanged = PublishSubject.create();

    ObservableList(List<E> list) {
        this.list = list;
    }

    /**
     * Returns the list used internally to store elements.
     */
    List<E> getList() {
        return list;
    }

    @Override
    public int size() {
        return getList().size();
    }

    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getList().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return getList().iterator();
    }

    @Override
    public Object[] toArray() {
        return getList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getList().toArray(a);
    }

    @Override
    public boolean add(E o) {
        boolean changed = getList().add(o);
        if (changed) {
            added.onNext(Indexed.of(getList().size() - 1, o));
        }
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        int idx = getList().indexOf(o);
        if (idx != -1) {
            remove(idx);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getList().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return c.stream().filter(this::add).count() != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c) {
            add(index++, e);
        }
        return c.size() != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            while (remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Set<Object> objects = new HashSet<>(c);
        int idx = 0;
        while (idx < getList().size()) {
            if (!objects.contains(getList().get(idx))) {
                remove(idx);
                changed = true;
            } else {
                idx++;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        while (!getList().isEmpty()) {
            remove(0);
        }
    }

    @Override
    public E get(int index) {
        return getList().get(index);
    }

    @Override
    public E set(int index, E element) {
        E old = getList().set(index, element);
        updated.onNext(Indexed.of(index, element));
        if (!Objects.equals(old, element)) {
            updatedChanged.onNext(Indexed.of(index, element));
        }
        return old;
    }

    @Override
    public void add(int index, E element) {
        getList().add(index, element);
        added.onNext(Indexed.of(index, element));
    }

    @Override
    public E remove(int index) {
        E old = getList().remove(index);
        removed.onNext(Indexed.of(index, old));
        return old;
    }

    @Override
    public int indexOf(Object o) {
        return getList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getList().lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return getList().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return getList().listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return getList().subList(fromIndex, toIndex);
    }

    /**
     * Returns an observable which emits a value when a new element is added to the list. The value emitted is an
     * {@link Indexed<E>} object, which contains the index of the added element in the list and the element itself.
     */
    public Observable<Indexed<E>> onAdded() {
        return Observable.wrap(added);
    }

    /**
     * Returns an observable which emits a value when an element is removed from the list. The value emitted is an
     * {@link Indexed<E>} object, which contains the index of the removed element in the list and the element itself.
     */
    public Observable<Indexed<E>> onRemoved() {
        return Observable.wrap(removed);
    }

    /**
     * Returns an observable which emits a value when an element in the list updated with a new value. The value emitted
     * is an {@link Indexed<E>} object, which contains the index of the updated element in the list and the element
     * itself.
     */
    public Observable<Indexed<E>> onUpdated() {
        return Observable.wrap(updated);
    }

    /**
     * Returns an observable which emits a value when an element in the list updated with a new value, which differs
     * from the old value at that index. The value emitted is an {@link Indexed<E>} object, which contains the index of
     * the updated element in the list and the element itself.
     */
    public Observable<Indexed<E>> onUpdatedChanged() {
        return Observable.wrap(updatedChanged);
    }

}