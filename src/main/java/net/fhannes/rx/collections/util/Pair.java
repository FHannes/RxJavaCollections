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

import java.util.Objects;

/**
 * A utility class representing a pair of objects.
 *
 * @param <A> The type of the first object.
 * @param <B> The type of the second object.
 */
public class Pair<A, B> {

    private A a;
    private B b;

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public static <A, B> Pair<A, B> of(Pair<A, ?> p, B b) {
        return new Pair<>(p.getA(), b);
    }

    public static <A, B> Pair<A, B> of(A a, Pair<?, B> p) {
        return new Pair<>(a, p.getB());
    }

    Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Pair &&
                Objects.equals(getA(), ((Pair) o).getA()) && Objects.equals(getB(), ((Pair) o).getB());

    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

}
