package trees.b;
/*
 * B-tree set (Java)
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

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedSet;
import java.util.Stack;


public final class BTree<E extends Comparable<? super E>>
        extends AbstractSet<E> implements SortedSet<E> {

    /*---- Fields ----*/

    private BNode<E> root;  // Not null
    private int size;  // Non-negative

    private final int minKeys;  // At least 1, equal to degree-1
    private final int maxKeys;  // At least 3, odd number, equal to minKeys*2+1

    /*---- Constructors ----*/

    // The degree is the minimum number of children each non-root internal node must have.
    public BTree(int degree) {
        if (degree < 2)
            throw new IllegalArgumentException("Degree must be at least 2");
        if (degree * 2L > Integer.MAX_VALUE)  // In other words, need maxChildren <= INT_MAX
            throw new IllegalArgumentException("Degree too large");
        minKeys = degree - 1;
        maxKeys = degree * 2 - 1;
        clear();
    }


    public BTree(int degree, Collection<E> coll) {
        this(degree);
        addAll(coll);
    }

    /*---- Methods ----*/
    public int size() {
        return size;
    }

    public void clear() {
        root = new BNode<>(maxKeys, true);
        size = 0;
    }

    public boolean contains(Object obj) {
        Objects.requireNonNull(obj);
        @SuppressWarnings("unchecked")
        E key = (E)obj;

        // Walk down the tree
        BNode<E> node = root;
        while (true) {
            int index = node.search(key);
            if (index >= 0)
                return true;
            else if (node.isLeaf())
                return false;
            else  // Internal node
                node = node.children[~index];
        }
    }

    public boolean add(E obj) {
        Objects.requireNonNull(obj);

        // Special preprocessing to split root node
        if (root.numKeys == maxKeys) {
            BNode<E> child = root;
            root = new BNode<>(maxKeys, false);  // Increment tree height
            root.children[0] = child;
            root.splitChild(0);
        }

        // Walk down the tree
        BNode<E> node = root;
        while (true) {
            // Search for index in current node
            assert node.numKeys < maxKeys;
            assert node == root || node.numKeys >= minKeys;
            int index = node.search(obj);
            if (index >= 0)
                return false;  // Key already exists in tree
            index = ~index;
            assert index >= 0;

            if (node.isLeaf()) {  // Simple insertion into leaf
                if (size == Integer.MAX_VALUE)
                    throw new IllegalStateException("Maximum size reached");
                node.insertKeyAndChild(index, obj, -1, null);
                size++;
                return true;

            } else {  // Handle internal node
                BNode<E> child = node.children[index];
                if (child.numKeys == maxKeys) {  // Split child node
                    node.splitChild(index);
                    int cmp = obj.compareTo(node.keys[index]);
                    if (cmp == 0)
                        return false;  // Key already exists in tree
                    else if (cmp > 0)
                        child = node.children[index + 1];
                }
                node = child;
            }
        }
    }

    public boolean remove(Object obj) {
        Objects.requireNonNull(obj);
        @SuppressWarnings("unchecked")
        E key = (E)obj;

        // Walk down the tree
        int index = root.search(key);
        BNode<E> node = root;
        while (true) {
            assert node.numKeys <= maxKeys;
            assert node == root || node.numKeys > minKeys;
            if (node.isLeaf()) {
                if (index >= 0) {  // Simple removal from leaf
                    node.removeKeyAndChild(index, -1);
                    assert size > 0;
                    size--;
                    return true;
                } else
                    return false;

            } else {  // Internal node
                if (index >= 0) {  // Key is stored at current node
                    BNode<E> left  = node.children[index + 0];
                    BNode<E> right = node.children[index + 1];
                    assert left != null && right != null;
                    if (left.numKeys > minKeys) {  // Replace key with predecessor
                        node.keys[index] = left.removeMax();
                        assert size > 0;
                        size--;
                        return true;
                    } else if (right.numKeys > minKeys) {  // Replace key with successor
                        node.keys[index] = right.removeMin();
                        assert size > 0;
                        size--;
                        return true;
                    } else {  // Merge key and right node into left node, then recurse
                        node.mergeChildren(index);
                        if (node == root && root.numKeys == 0) {
                            root = root.children[0];  // Decrement tree height
                            assert root != null;
                        }
                        node = left;
                        index = minKeys;  // Index known due to merging; no need to search
                    }

                } else {  // Key might be found in some child
                    BNode<E> child = node.ensureChildRemove(~index);
                    if (node == root && root.numKeys == 0) {
                        root = root.children[0];  // Decrement tree height
                        assert root != null;
                    }
                    node = child;
                    index = node.search(key);
                }
            }
        }
    }

    public E first() {
        if (size == 0)
            throw new NoSuchElementException();
        BNode<E> node = root;
        while (!node.isLeaf())
            node = node.children[0];
        return node.keys[0];
    }

    public E last() {
        if (size == 0)
            throw new NoSuchElementException();
        BNode<E> node = root;
        while (!node.isLeaf())
            node = node.children[node.numKeys];
        return node.keys[node.numKeys - 1];
    }

    // Note: Not fail-fast on concurrent modification.
    public Iterator<E> iterator() {
        return new Iter();
    }

    public Comparator<? super E> comparator() {
        return new Comparator<E>() {  // Natural ordering
            public int compare(E x, E y) {
                return x.compareTo(y);
            }
        };
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
        throw new UnsupportedOperationException();
    }

    public SortedSet<E> headSet(E toElement) {
        throw new UnsupportedOperationException();
    }

    public SortedSet<E> tailSet(E fromElement) {
        throw new UnsupportedOperationException();
    }

    // For unit tests
    public void checkStructure() {
        // Check size and root node properties
        if (root == null || size < 0 || size > maxKeys && root.isLeaf()
                || size <= minKeys * 2 && (!root.isLeaf() || root.numKeys != size))
            throw new AssertionError("Invalid size or root type");

        // Calculate height by descending into one branch
        int height = 0;
        for (BNode<E> node = root; !node.isLeaf(); node = node.children[0])
            height++;

        // Check all nodes and total size
        if (root.checkStructure(true, height, null, null) != size)
            throw new AssertionError("Size mismatch");
    }




    /*---- Helper class: B-tree iterator ----*/

    private final class Iter implements Iterator<E> {

        /*-- Fields --*/

        private Stack<BNode<E>> nodeStack;
        private Stack<Integer> indexStack;


        /*-- Constructors --*/

        public Iter() {
            nodeStack  = new Stack<>();
            indexStack = new Stack<>();
            if (root.numKeys > 0)
                pushLeftPath(root);
        }


        /*-- Methods --*/

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }


        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            BNode<E> node = nodeStack.peek();
            int index = indexStack.pop();
            E result = node.keys[index];
            index++;
            if (index < node.numKeys)
                indexStack.push(index);
            else
                nodeStack.pop();
            if (!node.isLeaf())
                pushLeftPath(node.children[index]);
            return result;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }


        private void pushLeftPath(BNode<E> node) {
            while (true) {
                nodeStack.push(node);
                indexStack.push(0);
                if (node.isLeaf())
                    break;
                node = node.children[0];
            }
        }

    }

}