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
