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
package net.fhannes.rx.collections.util;

/**
 * A subclass of {@link Pair} which represents an object and some integer index representing it.
 */
public class Indexed<T> extends Pair<Integer, T> {

    public static <T> Indexed<T> of(int index, T value) {
        return new Indexed<>(index, value);
    }

    private Indexed(int index, T value) {
        super(index, value);
    }

    public int getIndex() {
        return getA();
    }

    public T getValue() {
        return getB();
    }

}
