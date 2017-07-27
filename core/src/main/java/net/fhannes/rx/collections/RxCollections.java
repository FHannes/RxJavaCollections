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

import java.util.*;

/**
 * Contains static methods to convert any supported collection to a reactive collection.
 */
public final class RxCollections {

    /**
     * This is a utility class with static methods, it should not be instantiated.
     */
    private RxCollections() {

    }

    /**
     * Creates a reactive list, wrapped around the given list.
     *
     * @param list A given list.
     * @param <E> The type of the elements stored in the given list.
     * @return A reactive {@link ObservableList} instance.
     */
    public static <E> ObservableList<E> of(List<E> list) {
        if (list instanceof ArrayList) {
            return new ObservableArrayList<>((ArrayList<E>) list);
        } else if (list instanceof LinkedList) {
            return new ObservableLinkedList<>((LinkedList<E>) list);
        }
        return new ObservableList<>(list);
    }

    /**
     * Creates a reactive set, wrapped around the given set.
     *
     * @param set A given set.
     * @param <E> The type of the elements stored in the given set.
     * @return A reactive {@link ObservableList} instance.
     */
    public static <E> ObservableSet<E> of(Set<E> set) {
        if (set instanceof HashSet) {
            return new ObservableHashSet<>((HashSet<E>) set);
        }
        return new ObservableSet<>(set);
    }

}
