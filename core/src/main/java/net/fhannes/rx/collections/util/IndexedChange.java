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
 * A subclass of {@link Pair} which represents an indexed object which is updated in some way to a new indexed object.
 */
public class IndexedChange<T> extends Pair<Indexed<T>, Indexed<T>> {

    public static <T> IndexedChange<T> of(int oldIndex, T oldValue, int newIndex, T newValue) {
        return new IndexedChange<>(Indexed.of(oldIndex, oldValue), Indexed.of(newIndex, newValue));
    }

    public static <T> IndexedChange<T> of(Indexed<T> oldValue, Indexed<T> newValue) {
        return new IndexedChange<>(oldValue, newValue);
    }

    private IndexedChange(Indexed<T> oldValue, Indexed<T> newValue) {
        super(oldValue, newValue);
    }

    public Indexed<T> getOldValue() {
        return getA();
    }

    public Indexed<T> getNewValue() {
        return getB();
    }

}
