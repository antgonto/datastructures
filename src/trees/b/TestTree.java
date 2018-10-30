package trees.b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Test;

public class TestTree {

    /*
     * B-tree set test (Java)
     *
     * Copyright (c) 2018 Project Nayuki. (MIT License)
     * https://www.nayuki.io/page/btree-set
     *
     * Permission is hereby granted, free of charge, to any person obtaining a copy of
     * this software and associated documentation files (the "Software"), to deal in
     * the Software without restriction, including without limitation the rights to
     * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
     * the Software, and to permit persons to whom the Software is furnished to do so,
     * subject to the following conditions:
     * - The above copyright notice and this permission notice shall be included in
     *   all copies or substantial portions of the Software.
     * - The Software is provided "as is", without warranty of any kind, express or
     *   implied, including but not limited to the warranties of merchantability,
     *   fitness for a particular purpose and noninfringement. In no event shall the
     *   authors or copyright holders be liable for any claim, damages or other
     *   liability, whether in an action of contract, tort or otherwise, arising from,
     *   out of or in connection with the Software or the use or other dealings in the
     *   Software.
     */

        @Test public void testSmallRandomly() {
            final int trials = 1000;
            final int operations = 100;
            final int range = 1000;

            for (int i = 0; i < trials; i++) {
                SortedSet<Integer> set0 = new TreeSet<>();
                BTree<Integer> set1 = new BTree<>(rand.nextInt(5) + 2);
                for (int j = 0; j < operations; j++) {
                    // Add/remove a random value
                    Integer val = rand.nextInt(range);
                    if (rand.nextDouble() < 0.001) {
                        set0.clear();
                        set1.clear();
                    } else if (rand.nextDouble() < 0.5)
                        assertTrue(set0.add(val) == set1.add(val));
                    else
                        assertTrue(set0.remove(val) == set1.remove(val));
                    set1.checkStructure();

                    // Check size and check element membership over entire range
                    assertTrue(set0.isEmpty() == set1.isEmpty());
                    assertEquals(set0.size(), set1.size());
                    if (!set0.isEmpty()) {
                        assertEquals(set0.first(), set1.first());
                        assertEquals(set0.last (), set1.last ());
                    }
                    for (int k = -4; k < range + 4; k++) {
                        val = k;
                        assertTrue(set0.contains(val) == set1.contains(val));
                    }
                }
            }
        }

        @Test public void testInsertRandomly() {
            final int trials = 100;
            final int operations = 10_000;
            final int range = 100_000;
            final int checks = 10;

            for (int i = 0; i < trials; i++) {
                Set<Integer> set0 = new HashSet<>();
                BTree<Integer> set1 = new BTree<>(2);
                for (int j = 0; j < operations; j++) {
                    // Add a random value
                    Integer val = rand.nextInt(range);
                    assertTrue(set0.add(val) == set1.add(val));
                    if (rand.nextDouble() < 0.003)
                        set1.checkStructure();

                    // Check size and random element membership
                    assertEquals(set0.size(), set1.size());
                    for (int k = 0; k < checks; k++) {
                        val = rand.nextInt(range);
                        assertTrue(set0.contains(val) == set1.contains(val));
                    }
                }
            }
        }

        @Test public void testLargeRandomly() {
            final int trials = 100;
            final int operations = 30_000;
            final int range = 100_000;
            final int checks = 10;

            for (int i = 0; i < trials; i++) {
                Set<Integer> set0 = new HashSet<>();
                BTree<Integer> set1 = new BTree<>(rand.nextInt(5) + 2);
                for (int j = 0; j < operations; j++) {
                    // Add/remove a random value
                    Integer val = rand.nextInt(range);
                    if (rand.nextDouble() < 0.5)
                        assertTrue(set0.add(val) == set1.add(val));
                    else
                        assertTrue(set0.remove(val) == set1.remove(val));
                    if (rand.nextDouble() < 0.001)
                        set1.checkStructure();

                    // Check size and random element membership
                    assertEquals(set0.size(), set1.size());
                    for (int k = 0; k < checks; k++) {
                        val = rand.nextInt(range);
                        assertTrue(set0.contains(val) == set1.contains(val));
                    }
                }
            }
        }

        @Test public void testRemoveAllRandomly() {
            final int trials = 100;
            final int limit = 10_000;
            final int range = 100_000;
            final int checks = 10;

            for (int i = 0; i < trials; i++) {
                // Create sets and add all values
                Set<Integer> set0 = new HashSet<>();
                BTree<Integer> set1 = new BTree<>(rand.nextInt(5) + 2);
                for (int j = 0; j < limit; j++) {
                    Integer val = rand.nextInt(range);
                    assertTrue(set0.add(val) == set1.add(val));
                }
                set1.checkStructure();

                // Remove each value in random order
                List<Integer> list = new ArrayList<>(set0);
                Collections.shuffle(list);
                for (Integer val : list) {
                    assertTrue(set0.remove(val) == set1.remove(val));
                    if (rand.nextDouble() < Math.max(1.0 / set1.size(), 0.001))
                        set1.checkStructure();
                    assertEquals(set0.size(), set1.size());
                    for (int k = 0; k < checks; k++) {
                        val = rand.nextInt(range);
                        assertTrue(set0.contains(val) == set1.contains(val));
                    }
                }
                assertTrue(set0.isEmpty() && set1.isEmpty());
            }
        }


        @Test public void testIteratorRandomly() {
            final int trials = 10_000;
            final int operations = 1000;
            final int range = 10_000;

            for (int i = 0; i < trials; i++) {
                // Create sets and add all values
                Set<Integer> set0 = new HashSet<>();
                BTree<Integer> set1 = new BTree<>(rand.nextInt(5) + 2);
                int numInsert = rand.nextInt(operations);
                for (int j = 0; j < numInsert; j++) {
                    Integer val = rand.nextInt(range);
                    assertTrue(set0.add(val) == set1.add(val));
                }
                assertEquals(set0, new HashSet<>(set1));

                // Remove a random subset
                List<Integer> list = new ArrayList<>(set0);
                Collections.shuffle(list);
                int numRemove = rand.nextInt(list.size() + 1);
                for (Integer val : list.subList(0, numRemove))
                    assertTrue(set0.remove(val) == set1.remove(val));
                assertEquals(set0, new HashSet<>(set1));
            }
        }

        private static final Random rand = new Random();

}