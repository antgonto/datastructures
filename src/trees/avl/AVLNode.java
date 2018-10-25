package trees.avl;

public class AVLNode {
    private int key = 0;
    private int height = 0;
    private AVLNode left = null;
    private AVLNode right = null;

    public AVLNode(int d) {
        key = d;
        height = 1;
    }

    public int getKey() {
        return key;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setLeft(AVLNode left){
        this.left = left;
    }

    public AVLNode getLeft(){
        return left;
    }

    public void setRight(AVLNode right){
        this.right = right;
    }

    public AVLNode getRight(){
        return right;
    }

}