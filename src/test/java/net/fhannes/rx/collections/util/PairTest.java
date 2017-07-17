package net.fhannes.rx.collections.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test unit for the {@link Pair} class.
 */
public class PairTest {

    @Test
    public void of() throws Exception {
        String a = "String";
        Integer b = 5;

        Pair<String, Integer> pair = Pair.of(a, b);

        assertEquals(pair.getA(), a);
        assertEquals(pair.getB(), b);
    }

    @Test
    public void of1() throws Exception {
        String a = "String";
        Integer b = 5;
        Float c = 3F;

        Pair<String, Float> pair = Pair.of(Pair.of(a, b), c);

        assertEquals(pair.getA(), a);
        assertEquals(pair.getB(), c);
    }

    @Test
    public void of2() throws Exception {
        String a = "String";
        Integer b = 5;
        Float c = 3F;

        Pair<Float, Integer> pair = Pair.of(c, Pair.of(a, b));

        assertEquals(pair.getA(), c);
        assertEquals(pair.getB(), b);
    }

}