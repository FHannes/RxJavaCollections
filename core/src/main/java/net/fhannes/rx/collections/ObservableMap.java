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

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import net.fhannes.rx.collections.util.EntryChange;
import net.fhannes.rx.collections.util.ImmutableEntry;

import java.util.*;

public class ObservableMap<K, V> implements Map<K, V> {

    private Map<K, V> map;
    private BehaviorSubject<Map<K, V>> items = BehaviorSubject.create();
    private PublishSubject<Map.Entry<K, V>> put = PublishSubject.create();
    private PublishSubject<Map.Entry<K, V>> added = PublishSubject.create();
    private PublishSubject<Map.Entry<K, V>> removed = PublishSubject.create();
    private PublishSubject<EntryChange<K, V>> updated = PublishSubject.create();

    private boolean updating = false;

    private void changed() {
        if (!updating) {
            items.onNext(Collections.unmodifiableMap(new HashMap<>(this)));
        }
    }

    private void beginUpdate() {
        updating = true;
    }

    private void endUpdate(boolean changed) {
        updating = false;
        if (changed) {
            changed();
        }
    }

    ObservableMap(Map<K, V> map) {
        this.map = map;
        changed();
    }

    /**
     * Returns the map used internally to store elements.
     */
    Map<K, V> getList() {
        return map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        boolean keyExists = map.containsKey(key);
        V oldValue = map.put(key, value);
        put.onNext(ImmutableEntry.of(key, value));
        if (keyExists) {
            updated.onNext(EntryChange.of(key, oldValue, value));
        } else {
            added.onNext(ImmutableEntry.of(key, value));
        }
        changed();
        return value;
    }

    @Override
    public V remove(Object key) {
        V value = map.remove(key);
        removed.onNext(ImmutableEntry.of((K) key, value));
        changed();
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        beginUpdate();
        try {
            m.forEach(this::put);
        } finally {
            endUpdate(!m.isEmpty());
        }
    }

    @Override
    public void clear() {
        map.clear();
        map.forEach((k, v) -> removed.onNext(ImmutableEntry.of(k, v)));
        changed();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public Observable<Entry<K, V>> observable() {
        return Observable.fromIterable(map.entrySet());
    }

    public Observable<Map<K, V>> observableChanges() {
        return Observable.wrap(items);
    }

    private Observable<Map.Entry<K, V>> onPut() {
        return Observable.wrap(put);
    }

    private Observable<Map.Entry<K, V>> onAdded() {
        return Observable.wrap(added);
    }

    private Observable<Map.Entry<K, V>> onRemoved() {
        return Observable.wrap(removed);
    }

    private Observable<EntryChange<K, V>> onUpdated() {
        return Observable.wrap(updated);
    }

}
