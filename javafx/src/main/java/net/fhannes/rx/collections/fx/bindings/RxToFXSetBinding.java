package net.fhannes.rx.collections.fx.bindings;

import io.reactivex.Scheduler;
import net.fhannes.rx.collections.ObservableSet;
import net.fhannes.rx.collections.util.Disposer;

import java.util.Set;

/**
 * Binds an {@link ObservableSet} object from RxJavaCollections to an {@link javafx.collections.ObservableSet} object
 * from JavaFX, such that every update of the Rx object are automatically reflected in the FX object. These updates are
 * performed in the FX thread. It is important to dispose of this object after use, as it uses infinite subscriptions.
 *
 * @param <E> The type of elements stored in the set.
 */
public class RxToFXSetBinding<E> implements FXCollectionBinding<E, Set<E>, ObservableSet<E>,
        javafx.collections.ObservableSet<E>> {

    private ObservableSet<E> rxSet;
    private javafx.collections.ObservableSet<E> fxSet;

    private Disposer disposer = new Disposer();

    public RxToFXSetBinding(ObservableSet<E> rxSet, javafx.collections.ObservableSet<E> fxSet) {
        this.rxSet = rxSet;
        this.fxSet = fxSet;
        fxSet.clear();
        disposer.register(rxSet.observable().subscribe(fxSet::add));
        disposer.register(rxSet.onAdded().subscribe(fxSet::add));
        disposer.register(rxSet.onRemoved().subscribe(fxSet::remove));
    }

    public RxToFXSetBinding(Scheduler scheduler, ObservableSet<E> rxSet, javafx.collections.ObservableSet<E> fxSet) {
        this.rxSet = rxSet;
        this.fxSet = fxSet;
        fxSet.clear();
        disposer.register(rxSet.observable().observeOn(scheduler).subscribe(fxSet::add));
        disposer.register(rxSet.onAdded().observeOn(scheduler).subscribe(fxSet::add));
        disposer.register(rxSet.onRemoved().observeOn(scheduler).subscribe(fxSet::remove));
    }

    @Override
    public ObservableSet<E> rx() {
        return rxSet;
    }

    @Override
    public javafx.collections.ObservableSet<E> fx() {
        return fxSet;
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
