package net.fhannes.rx.collections.fx.bindings;

import io.reactivex.Scheduler;
import net.fhannes.rx.collections.ObservableList;
import net.fhannes.rx.collections.util.Disposer;

import java.util.List;

/**
 * Binds an {@link ObservableList} object from RxJavaCollections to an {@link javafx.collections.ObservableList} object
 * from JavaFX, such that every update of the Rx object are automatically reflected in the FX object. These updates are
 * performed in the FX thread. It is important to dispose of this object after use, as it uses infinite subscriptions.
 *
 * @param <E> The type of elements stored in the list.
 */
public class RxToFXListBinding<E> implements FXCollectionBinding<E, List<E>, ObservableList<E>,
        javafx.collections.ObservableList<E>> {

    private ObservableList<E> rxList;
    private javafx.collections.ObservableList<E> fxList;

    private Disposer disposer = new Disposer();

    public RxToFXListBinding(ObservableList<E> rxList, javafx.collections.ObservableList<E> fxList) {
        this.rxList = rxList;
        this.fxList = fxList;
        fxList.clear();
        disposer.register(rxList.observable().subscribe(fxList::add));
        disposer.register(rxList.onAdded().subscribe(c -> fxList.add(c.getIndex(), c.getValue())));
        disposer.register(rxList.onRemoved().subscribe(c -> fxList.remove(c.getIndex())));
        disposer.register(rxList.onUpdatedChanged().subscribe(c ->
                fxList.set(c.getNewValue().getIndex(), c.getOldValue().getValue())));
        disposer.register(rxList.onMoved().subscribe(c ->
                fxList.add(c.getNewValue().getIndex(), fxList.remove(c.getOldValue().getIndex()))));
    }

    public RxToFXListBinding(Scheduler scheduler, ObservableList<E> rxList, javafx.collections.ObservableList<E> fxList) {
        this.rxList = rxList;
        this.fxList = fxList;
        fxList.clear();
        disposer.register(rxList.observable().observeOn(scheduler).subscribe(fxList::add));
        disposer.register(rxList.onAdded().observeOn(scheduler).subscribe(c -> fxList.add(c.getIndex(), c.getValue())));
        disposer.register(rxList.onRemoved().observeOn(scheduler).subscribe(c -> fxList.remove(c.getIndex())));
        disposer.register(rxList.onUpdatedChanged().observeOn(scheduler).subscribe(c ->
                fxList.set(c.getNewValue().getIndex(), c.getOldValue().getValue())));
        disposer.register(rxList.onMoved().observeOn(scheduler).subscribe(c ->
                fxList.add(c.getNewValue().getIndex(), fxList.remove(c.getOldValue().getIndex()))));
    }

    @Override
    public ObservableList<E> rx() {
        return rxList;
    }

    @Override
    public javafx.collections.ObservableList<E> fx() {
        return fxList;
    }

    @Override
    public void dispose() {
        disposer.dispose();
    }

    @Override
    public boolean isDisposed() {
        return disposer.isDisposed();
    }

}
