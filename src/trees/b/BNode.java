package trees.b;

/*---- Helper class: B-tree node ----*/

import java.util.Arrays;

public class BNode<E extends Comparable<? super E>> {

    /*-- Fields --*/

    public final E[] keys;  // Length equal to maxKeys, not null
    public final BNode<E>[] children;  // Null if leaf node, length maxKeys+1 if internal node
    public int numKeys;  // Range is [0, maxKeys] for root, but [minKeys, maxKeys] for all other nodes

    /*-- Constructor --*/

    // Note: Once created, a node's structure never changes between a leaf and internal node.
    @SuppressWarnings("unchecked")
    public BNode(int maxKeys, boolean leaf) {
        assert maxKeys >= 3 && maxKeys % 2 == 1;
        keys = (E[])new Comparable[maxKeys];
        children = leaf ? null : new BNode[maxKeys + 1];
        numKeys = 0;
    }

    /*-- Methods for getting info --*/

    private int minKeys() {
        return keys.length / 2;
    }

    private int maxKeys() {
        return keys.length;
    }

    public boolean isLeaf() {
        return children == null;
    }

    // Searches this node's keys array and returns i (non-negative) if obj equals keys[i],
    // otherwise returns ~i (negative) if children[i] should be explored. For simplicity,
    // the implementation uses linear search. It's possible to replace it with binary search for speed.
    public int search(E obj) {
        int i = 0;
        while (i < numKeys) {
            int cmp = obj.compareTo(keys[i]);
            if (cmp == 0) {
                assert 0 <= i && i < numKeys;
                return i;  // Key found
            } else if (cmp > 0)
                i++;
            else  // cmp < 0
                break;
        }
        assert 0 <= i && i <= numKeys;
        return ~i;  // Not found, caller should recurse on child
    }

    /*-- Methods for basic manipulation --*/

    // Inserts the given key and child into this node's arrays at the given indices, incrementing the number of keys.
    public void insertKeyAndChild(int keyIndex, E key, int childIndex, BNode<E> child) {
        assert 0 <= numKeys && numKeys < maxKeys() && key != null;
        assert 0 <= keyIndex && keyIndex <= numKeys;

        // Handle children array
        if (isLeaf())
            assert childIndex == -1 && child == null;
        else {
            assert 0 <= childIndex && childIndex <= numKeys + 1 && child != null;
            System.arraycopy(children, childIndex, children, childIndex + 1, numKeys + 1 - childIndex);
            children[childIndex] = child;
        }

        // Handle keys array
        System.arraycopy(keys, keyIndex, keys, keyIndex + 1, numKeys - keyIndex);
        keys[keyIndex] = key;
        numKeys++;
    }

    // Removes and returns this node's key at the given index, decrementing the number of keys.
    // Also must remove a child at the given index if this is not a leaf node.
    public E removeKeyAndChild(int keyIndex, int childIndex) {
        assert 1 <= numKeys && numKeys <= keys.length;
        assert 0 <= keyIndex && keyIndex < numKeys;

        // Handle children array
        if (isLeaf())
            assert childIndex == -1;
        else {
            assert 0 <= childIndex && childIndex <= numKeys;
            assert children[childIndex] != null;
            System.arraycopy(children, childIndex + 1, children, childIndex, numKeys - childIndex);
            children[numKeys] = null;
        }

        // Handle keys array
        E result = keys[keyIndex];
        assert result != null;
        System.arraycopy(keys, keyIndex + 1, keys, keyIndex, numKeys - 1 - keyIndex);
        keys[numKeys - 1] = null;
        numKeys--;
        return result;
    }

    /*-- Methods for insertion --*/

    // For the child node at the given index, this moves the right half of keys and children to a new node,
    // and adds the middle key and new child to this node. The left half of child's data is not moved.
    public void splitChild(int index) {
        assert !this.isLeaf() && 0 <= index && index <= this.numKeys && this.numKeys < maxKeys();
        BNode<E> left = this.children[index];
        assert left.numKeys == maxKeys();
        BNode<E> right = new BNode<>(maxKeys(), left.isLeaf());
        int minKeys = minKeys();

        // Handle children
        if (!left.isLeaf()) {
            System.arraycopy(left.children, minKeys + 1, right.children, 0, minKeys + 1);
            Arrays.fill(left.children, minKeys + 1, left.children.length, null);
        }

        // Handle keys
        E middleKey = left.keys[minKeys];
        System.arraycopy(left.keys, minKeys + 1, right.keys, 0, minKeys);
        Arrays.fill(left.keys, minKeys, left.keys.length, null);
        left.numKeys = minKeys;
        right.numKeys = minKeys;

        this.insertKeyAndChild(index, middleKey, index + 1, right);
    }

    /*-- Methods for removal --*/

    // Performs modifications to ensure that this node's child at the given index has at least
    // minKeys+1 keys in preparation for a single removal. The child may gain a key and subchild
    // from its sibling, or it may be merged with a sibling, or nothing needs to be done.
    // A reference to the appropriate child is returned, which is helpful if the old child no longer exists.
    public BNode<E> ensureChildRemove(int index) {
        // Preliminaries
        assert !this.isLeaf() && 0 <= index && index <= this.numKeys;
        BNode<E> child = children[index];
        if (child.numKeys > minKeys())  // Already satisfies the condition
            return child;
        assert child.numKeys == minKeys();

        // Get siblings
        BNode<E> left = index >= 1 ? this.children[index - 1] : null;
        BNode<E> right = index < this.numKeys ? this.children[index + 1] : null;
        boolean internal = !child.isLeaf();
        assert left != null || right != null;  // At least one sibling exists because degree >= 2
        assert left  == null || left .isLeaf() != internal;  // Sibling must be same type (internal/leaf) as child
        assert right == null || right.isLeaf() != internal;  // Sibling must be same type (internal/leaf) as child

        if (left != null && left.numKeys > minKeys()) {  // Steal rightmost item from left sibling
            child.insertKeyAndChild(0, this.keys[index - 1],
                    (internal ? 0 : -1), (internal ? left.children[left.numKeys] : null));
            this.keys[index - 1] = left.removeKeyAndChild(left.numKeys - 1, (internal ? left.numKeys : -1));
            return child;
        } else if (right != null && right.numKeys > minKeys()) {  // Steal leftmost item from right sibling
            child.insertKeyAndChild(child.numKeys, this.keys[index],
                    (internal ? child.numKeys + 1 : -1), (internal ? right.children[0] : null));
            this.keys[index] = right.removeKeyAndChild(0, (internal ? 0 : -1));
            return child;
        } else if (left != null) {  // Merge child into left sibling
            this.mergeChildren(index - 1);
            return left;  // This is the only case where the return value is different
        } else if (right != null) {  // Merge right sibling into child
            this.mergeChildren(index);
            return child;
        } else
            throw new AssertionError("Impossible condition");
    }

    // Merges the child node at index+1 into the child node at index,
    // assuming the current node is not empty and both children have minkeys.
    public void mergeChildren(int index) {
        assert !this.isLeaf() && 0 <= index && index < this.numKeys;
        BNode<E> left  = children[index + 0];
        BNode<E> right = children[index + 1];
        assert left.numKeys == minKeys() && right.numKeys == minKeys();
        if (!left.isLeaf())
            System.arraycopy(right.children, 0, left.children, minKeys() + 1, minKeys() + 1);
        left.keys[minKeys()] = removeKeyAndChild(index, index + 1);
        System.arraycopy(right.keys, 0, left.keys, minKeys() + 1, minKeys());
        left.numKeys = maxKeys();
    }

    // Removes and returns the minimum key among the whole subtree rooted at this node.
    // Requires this node to be preprocessed to have at least minKeys+1 keys.
    public E removeMin() {
        for (BNode<E> node = this; ; ) {
            assert node.numKeys > minKeys();
            if (node.isLeaf())
                return node.removeKeyAndChild(0, -1);
            else
                node = node.ensureChildRemove(0);
        }
    }

    // Removes and returns the maximum key among the whole subtree rooted at this node.
    // Requires this node to be preprocessed to have at least minKeys+1 keys.
    public E removeMax() {
        for (BNode<E> node = this; ; ) {
            assert node.numKeys > minKeys();
            if (node.isLeaf())
                return node.removeKeyAndChild(node.numKeys - 1, -1);
            else
                node = node.ensureChildRemove(node.numKeys);
        }
    }

    /*-- Miscellaneous methods --*/

    // Checks the structure recursively and returns the total number
    // of keys in the subtree rooted at this node. For unit tests.
    int checkStructure(boolean isRoot, int leafDepth, E min, E max) {
        // Check basic fields
        if (isLeaf() != (leafDepth == 0))
            throw new AssertionError("Incorrect leaf/internal node type");
        if (numKeys < 0 || numKeys > maxKeys())
            throw new AssertionError("Invalid number of keys");
        if (isRoot && !isLeaf() && numKeys <= 0)
            throw new AssertionError("Invalid number of keys");
        if (!isRoot && numKeys < minKeys())
            throw new AssertionError("Invalid number of keys");

        // Check keys for strict increasing order
        for (int i = 0; i < keys.length; i++) {
            E key = keys[i];
            if ((key != null) != (i < numKeys))
                throw new AssertionError("Invalid filling of key array");
            if (i < numKeys) {
                boolean fail = i == 0 && min != null && key.compareTo(min) <= 0;
                fail |= i >= 1 && key.compareTo(keys[i - 1]) <= 0;
                fail |= i == numKeys - 1 && max != null && key.compareTo(max) >= 0;
                if (fail)
                    throw new AssertionError("Invalid key ordering");
            }
        }

        // Check children recursively and count keys in this subtree
        long count = numKeys;
        if (!isLeaf()) {
            // Check children pointers and recurse
            for (int i = 0; i < children.length; i++) {
                if ((children[i] != null) != (i <= numKeys))
                    throw new AssertionError("Invalid filling of children array");
                if (i <= numKeys)
                    count += children[i].checkStructure(false, leafDepth - 1, (i == 0 ? min : keys[i - 1]), (i == numKeys ? max : keys[i]));
                if (count > Integer.MAX_VALUE)
                    throw new AssertionError("Size overflow");
            }
        }
        return (int)count;
    }
}