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

public class ImmutableEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public static <K, V> ImmutableEntry<K, V> of(K key, V value) {
        return new ImmutableEntry<>(key, value);
    }

    public static <K, V> ImmutableEntry<K, V> of(Map.Entry<K, V> entry) {
        return new ImmutableEntry<>(entry.getKey(), entry.getValue());
    }

    private ImmutableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        // Entry is immutable
        return getValue();
    }

}
