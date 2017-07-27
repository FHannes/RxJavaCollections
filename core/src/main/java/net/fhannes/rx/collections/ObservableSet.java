/*
 * This file is part of the RxJavaCollections library.
 * https://github.com/FHannes/RxJavaCollections
 *
 * Copyright (c) 2017, Frédéric Hannes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.fhannes.rx.collections;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

import java.util.*;

/**
 * This class is a reactive set. It is a wrapper around a standard {@link Set} object, providing various Observable
 * objects which emit values when operations are performed on the set.
 *
 * @param <E> The type of elements stored in the set.
 */
public class ObservableSet<E> implements Set<E> {

    private Set<E> set;
    private BehaviorSubject<Set<E>> items = BehaviorSubject.create();
    private PublishSubject<E> added = PublishSubject.create();
    private PublishSubject<E> removed = PublishSubject.create();

    private boolean updating = false;

    ObservableSet(Set<E> set) {
        this.set = set;
        changed();
    }

    private void changed() {
        if (!updating) {
            items.onNext(Collections.unmodifiableSet(this));
        }
    }

    private void beginUpdate() {
        updating = true;
    }

    private void endUpdate(boolean changed) {
        updating = false;
        changed();
    }

    /**
     * Returns the set used internally to store elements.
     */
    Set<E> getSet() {
        return set;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean changed = getSet().add(e);
        if (changed) {
            added.onNext(e);
            changed();
        }
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        boolean changed = getSet().remove(o);
        if (changed) {
            removed.onNext((E) o);
            changed();
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getSet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        beginUpdate();
        boolean changed = c.stream().filter(this::add).count() != 0;
        endUpdate(changed);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Set<Object> objects = new HashSet<>(c);
        beginUpdate();
        boolean changed = getSet().stream().filter(o -> !objects.contains(o)).filter(this::remove).count() != 0;
        endUpdate(changed);
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Set<Object> objects = new HashSet<>(c);
        beginUpdate();
        boolean changed = getSet().stream().filter(objects::contains).filter(this::remove).count() != 0;
        endUpdate(changed);
        return changed;
    }

    @Override
    public void clear() {
        getSet().clear();
    }

    /**
     * Returns an observable which emits all items stored in the set, before completing.
     *
     * @return The {@link Observable} object.
     */
    public Observable<E> observable() {
        return Observable.fromIterable(getSet());
    }

    /**
     * Emits a read-only copy of the list on subscription and whenever it the set is updated. If a method such as
     * {@link #addAll(Collection)} is used, it will emit a copy only once and only if the set was changed.
     *
     * @return The {@link Observable} object.
     */
    public Observable<Set<E>> observableChanges() {
        return Observable.wrap(items);
    }

    /**
     * Returns an observable which emits a value when a new element is added to the set. The value emitted is the
     * element added to the set.
     *
     * @return The {@link Observable} object.
     */
    public Observable<E> onAdded() {
        return Observable.wrap(added);
    }

    /**
     * Returns an observable which emits a value when an element is removed from the set. The value emitted is the
     * element removed from the set.
     *
     * @return The {@link Observable} object.
     */
    public Observable<E> onRemoved() {
        return Observable.wrap(removed);
    }

}
