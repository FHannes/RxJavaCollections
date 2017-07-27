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

import net.fhannes.rx.collections.ObservableList;
import net.fhannes.rx.collections.ObservableSet;

import java.util.HashSet;

/**
 * Contains static methods to create bindings between RxJavaCollections and JavaFX.
 */
public final class JavaFXAdaptor {

    /**
     * This is a utility class with static methods, it should not be instantiated.
     */
    private JavaFXAdaptor() {

    }

    /**
     * Creates an adaptor which maps an {@link ObservableList} object from RxJavaCollections onto a new
     * {@link javafx.collections.ObservableList} from JavaFX.
     *
     * @param list The given reactive list.
     * @param <E> The type of the elements stored in the list.
     * @return The newly constructed list which updates automatically when the given list is updated.
     */
    public static <E> javafx.collections.ObservableList<E> adapt(ObservableList<E> list) {
        javafx.collections.ObservableList<E> newList = javafx.collections.FXCollections.observableArrayList();
        list.observable().subscribe(newList::add);
        list.onAdded().subscribe(c -> newList.add(c.getIndex(), c.getValue()));
        list.onRemoved().subscribe(c -> newList.remove(c.getIndex()));
        list.onUpdatedChanged().subscribe(c -> newList.set(c.getIndex(), c.getValue()));
        return newList;
    }

    /**
     * Creates an adaptor which maps an {@link ObservableSet} object from RxJavaCollections onto a new
     * {@link javafx.collections.ObservableSet} from JavaFX.
     *
     * @param list The given reactive set.
     * @param <E> The type of the elements stored in the set.
     * @return The newly constructed set which updates automatically when the given set is updated.
     */
    public static <E> javafx.collections.ObservableSet<E> adapt(ObservableSet<E> list) {
        javafx.collections.ObservableSet<E> newSet = javafx.collections.FXCollections.observableSet(new HashSet<>());
        list.observable().subscribe(newSet::add);
        list.onAdded().subscribe(newSet::add);
        list.onRemoved().subscribe(newSet::remove);
        return newSet;
    }

}
