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

import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows a user to register multiple {@link Disposable} objects and dispose all of them with a single call.
 */
public class Disposer implements Disposable {

    private List<Disposable> disposables = new ArrayList<>();

    /**
     * Register a {@link Disposable} object with the disposer.
     *
     * @param disposable the given {@link Disposable} object.
     */
    public synchronized void register(Disposable disposable) {
        disposables.add(disposable);
    }

    /**
     * Dispose of all disposable objects currently registered with the disposer.
     */
    @Override
    public synchronized void dispose() {
        disposables.stream().filter(d -> !d.isDisposed()).forEach(Disposable::dispose);
        disposables.clear();
    }

    /**
     * Check whether all currently registered objects have been disposed off.
     *
     * @return True if all registered objects have been disposed of.
     */
    @Override
    public synchronized boolean isDisposed() {
        return disposables.stream().allMatch(Disposable::isDisposed);
    }

}
