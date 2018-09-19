package trees;

// public class Student extends Person implements Comparable<Person>
public class BinaryTree<T extends Comparable<? super T>> {
    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T element) {
        return this.contains(element, this.root);
    }

    private boolean contains(T element, TreeNode<T> node) {
        if (node == null) {
            return false;
        } else {
            int compareResult = element.compareTo(node.element);

            if (compareResult < 0)
                return contains(element, node.left);
            else if (compareResult > 0)
                return contains(element, node.right);
            else
                return true;
        }
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node == null)
            return null;
        else if (node.left == null)
            return node;
        else
            return findMin(node.left);
    }
    private TreeNode<T> findMax(TreeNode<T> node) {
        if (node!= null)
            while (node.right != null) {
                node = node.right;
            }
        return node;
    }

    public void insert(T element) {
        this.root = this.insert(element, this.root);
    }

    public TreeNode<T> insert(T element, TreeNode<T> current) {
        if (current == null)
            return new TreeNode<T>(element, null, null);

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

    public TreeNode<T> remove(T element, TreeNode<T> node) {
        if (node == null)
            return node;

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0)
            node.left= remove(element, node.left);
        else if (compareResult > 0)
            node.right = remove(element, node.right);
        else if (node.left != null && node.right != null){
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = node.left != null ? node.left : node.right;
        }
        return node;
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