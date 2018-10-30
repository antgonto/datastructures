package trees.binary;

import java.util.LinkedHashMap;

// public class Student extends Person implements Comparable<Person>
public class BinaryTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T element) {
        return this.contains(element, this.root);
    }

    private boolean contains(T element, BinaryNode<T> BNode) {
        if (BNode == null) {
            return false;
        } else {
            int compareResult = element.compareTo(BNode.element);

            if (compareResult < 0)
                return contains(element, BNode.left);
            else if (compareResult > 0)
                return contains(element, BNode.right);
            else
                return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> BNode) {
        if (BNode == null)
            return null;
        else if (BNode.left == null)
            return BNode;
        else
            return findMin(BNode.left);
    }
    private BinaryNode<T> findMax(BinaryNode<T> BNode) {
        if (BNode!= null)
            while (BNode.right != null) {
                BNode = BNode.right;
            }
        return BNode;
    }

    public void insert(T element) {
        this.root = this.insert(element, this.root);
    }

    public BinaryNode<T> insert(T element, BinaryNode<T> current) {
        if (current == null)
            return new BinaryNode<T>(element, null, null);

        int compareResult = element.compareTo(current.element);

        if (compareResult < 0)
            current.left = this.insert(element, current.left);
        else if (compareResult > 0)
            current.right = this.insert(element, current.right);

        return current;
    }

    public void remove(T element) {
        this.root = this.remove(element, this.root);
    }

    public BinaryNode<T> remove(T element, BinaryNode<T> BNode) {
        if (BNode == null)
            return BNode;

        int compareResult = element.compareTo(BNode.element);

        if (compareResult < 0)
            BNode.left= remove(element, BNode.left);
        else if (compareResult > 0)
            BNode.right = remove(element, BNode.right);
        else if (BNode.left != null && BNode.right != null){
            BNode.element = findMin(BNode.right).element;
            BNode.right = remove(BNode.element, BNode.right);
        } else {
            BNode = BNode.left != null ? BNode.left : BNode.right;
        }
        return BNode;
    }

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();


        bt.insert(10);
        bt.insert(13);
        bt.insert(11);
        bt.insert(12);
        bt.insert(7);
        bt.insert(8);
        bt.insert(6);
        bt.remove(7);
        System.out.println(bt.contains(12));

        System.out.println(bt.findMin(bt.root).element);
    }

}