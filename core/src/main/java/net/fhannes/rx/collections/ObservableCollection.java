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

import java.util.Collection;

public interface ObservableCollection<E, C extends Collection<E>> extends Collection<E> {

    /**
     * Returns an observable which emits all items stored in the collection, before completing.
     *
     * @return The {@link Observable} object.
     */
    Observable<E> observable();

    /**
     * Emits a read-only copy of the collection on subscription and whenever it the collection is updated. If a method
     * such as {@link #addAll(Collection)} is used, it will emit a copy only once and only if the collection was changed.
     *
     * @return The {@link Observable} object.
     */
    Observable<C> observableChanges();

}
