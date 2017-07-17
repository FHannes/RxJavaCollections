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