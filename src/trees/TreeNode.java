package trees;

public class TreeNode<T extends Comparable<? super T>> {
    public T element;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T element) {
        this(element, null, null);
    }

    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

}
