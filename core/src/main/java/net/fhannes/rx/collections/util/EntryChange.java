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

import java.util.Map;

/**
 * A subclass of {@link Pair} which represents an object which is updated in some way to a new object.
 */
public class EntryChange<K, V> extends Pair<Map.Entry<K, V>, Map.Entry<K, V>> {

    public static <K, V> EntryChange<K, V> of(Map.Entry<K, V> oldValue, Map.Entry<K, V> newValue) {
        return new EntryChange<>(ImmutableEntry.of(oldValue), ImmutableEntry.of(newValue));
    }

    public static <K, V> EntryChange<K, V> of(K key, V oldValue, V newValue) {
        return new EntryChange<>(ImmutableEntry.of(key, oldValue), ImmutableEntry.of(key, newValue));
    }

    private EntryChange(Map.Entry<K, V> oldValue, Map.Entry<K, V> newValue) {
        super(oldValue, newValue);
    }

    public Map.Entry<K, V> getOldEntry() {
        return getA();
    }

    public Map.Entry<K, V> getNewEntry() {
        return getB();
    }

}
