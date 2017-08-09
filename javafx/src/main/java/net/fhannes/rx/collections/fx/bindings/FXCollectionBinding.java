package net.fhannes.rx.collections.fx.bindings;

import io.reactivex.disposables.Disposable;
import net.fhannes.rx.collections.ObservableCollection;

import java.util.Collection;

/**
 * Represents a binding between an Rx collection and an FX collection. This binding can be disposed of when it is no
 * longer needed, in order to correctly manage the memory, as it deals with infinite Rx subscriptions.
 *
 * @param <E> The type of value stored in the collections.
 * @param <C> The type of collection handled by the binding.
 * @param <RXC> The type of Rx collection involved in the binding.
 * @param <FXC> The type of FX collection involved in the binding.
 */
interface FXCollectionBinding<E, C extends Collection<E>, RXC extends ObservableCollection<E, C>,
        FXC extends C> extends Disposable {

    /**
     * Return the Rx collection involved in the binding.
     *
     * @return The Rx collection.
     */
    RXC rx();

    /**
     * Return the FX collection involved in the binding.
     *
     * @return The FX collection.
     */
    FXC fx();

}
