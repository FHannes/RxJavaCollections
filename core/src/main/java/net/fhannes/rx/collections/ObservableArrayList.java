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

import java.util.ArrayList;

/**
 * This class is a reactive list. It is a specialized version of the {@link ObservableList} class, which wraps around
 * an {@link ArrayList} object.
 *
 * @param <E> The type of elements stored in the list.
 */
public class ObservableArrayList<E> extends ObservableList<E> {

    public ObservableArrayList() {
        this(new ArrayList<>());
    }

    public ObservableArrayList(ArrayList<E> list) {
        super(list);
    }

    @Override
    public void clear() {
        while (!getList().isEmpty()) {
            // By removing last element, the internal array can just be resized
            remove(getList().size() - 1);
        }
    }

}
