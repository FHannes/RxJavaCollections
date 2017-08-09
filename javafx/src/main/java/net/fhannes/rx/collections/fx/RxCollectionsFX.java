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
package net.fhannes.rx.collections.fx;

import io.reactivex.Scheduler;
import net.fhannes.rx.collections.ObservableList;
import net.fhannes.rx.collections.ObservableSet;
import net.fhannes.rx.collections.fx.bindings.RxToFXListBinding;
import net.fhannes.rx.collections.fx.bindings.RxToFXSetBinding;

/**
 * Contains static methods to create bindings between RxJavaCollections and JavaFX.
 */
public final class RxCollectionsFX {

    /**
     * This is a utility class with static methods, it should not be instantiated.
     */
    private RxCollectionsFX() {

    }

    /**
     * Creates a binding between list objects from RxJavaCollections and JavaFX, such that all changes in the Rx
     * object are pushed to the FX object. This object must be disposed of after use to avoid memory leaks.
     *
     * @param rxList A given {@link ObservableList} object from RxJavaCollections.
     * @param fxList A given {@link javafx.collections.ObservableList} object from JavaFX.
     * @param <E> The type of values stored in the list.
     * @return The disposable binding object which manages the binding between both lists.
     */
    public static <E> RxToFXListBinding<E> bind(ObservableList<E> rxList, javafx.collections.ObservableList<E> fxList) {
        return new RxToFXListBinding<>(rxList, fxList);
    }

    /**
     * Creates a binding between list objects from RxJavaCollections and JavaFX, such that all changes in the Rx
     * object are pushed to the FX object. This object must be disposed of after use to avoid memory leaks.
     *
     * @param scheduler The scheduler which should be used to push changes to the FX list. It is suggested to use the
     *                  platform scheduler available in the RxJavaFX library for this, if the FX list is part of a
     *                  visual component.
     * @param rxList A given {@link ObservableList} object from RxJavaCollections.
     * @param fxList A given {@link javafx.collections.ObservableList} object from JavaFX.
     * @param <E> The type of values stored in the list.
     * @return The disposable binding object which manages the binding between both lists.
     */
    public static <E> RxToFXListBinding<E> bind(Scheduler scheduler, ObservableList<E> rxList,
                                                javafx.collections.ObservableList<E> fxList) {
        return new RxToFXListBinding<>(scheduler, rxList, fxList);
    }

    /**
     * Creates a binding between set objects from RxJavaCollections and JavaFX, such that all changes in the Rx
     * object are pushed to the FX object. This object must be disposed of after use to avoid memory leaks.
     *
     * @param rxSet A given {@link ObservableSet} object from RxJavaCollections.
     * @param fxSet A given {@link javafx.collections.ObservableSet} object from JavaFX.
     * @param <E> The type of values stored in the set.
     * @return The disposable binding object which manages the binding between both sets.
     */
    public static <E> RxToFXSetBinding<E> bind(ObservableSet<E> rxSet, javafx.collections.ObservableSet<E> fxSet) {
        return new RxToFXSetBinding<>(rxSet, fxSet);
    }

    /**
     * Creates a binding between set objects from RxJavaCollections and JavaFX, such that all changes in the Rx
     * object are pushed to the FX object. This object must be disposed of after use to avoid memory leaks.
     *
     * @param scheduler The scheduler which should be used to push changes to the FX list. It is suggested to use the
     *                  platform scheduler available in the RxJavaFX library for this, if the FX list is part of a
     *                  visual component.
     * @param rxSet A given {@link ObservableSet} object from RxJavaCollections.
     * @param fxSet A given {@link javafx.collections.ObservableSet} object from JavaFX.
     * @param <E> The type of values stored in the set.
     * @return The disposable binding object which manages the binding between both sets.
     */
    public static <E> RxToFXSetBinding<E> bind(Scheduler scheduler, ObservableSet<E> rxSet,
                                               javafx.collections.ObservableSet<E> fxSet) {
        return new RxToFXSetBinding<>(scheduler, rxSet, fxSet);
    }

}
