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

import java.util.HashSet;

/**
 * This class is a reactive set. It is a specialized version of the {@link ObservableSet} class, which wraps around
 * a {@link HashSet} object.
 *
 * @param <E> The type of elements stored in the set.
 */
public class ObservableHashSet<E> extends ObservableSet<E> {

    public ObservableHashSet() {
        this(new HashSet<>());
    }

    public ObservableHashSet(HashSet<E> set) {
        super(set);
    }

}
