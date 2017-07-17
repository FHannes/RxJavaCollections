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
