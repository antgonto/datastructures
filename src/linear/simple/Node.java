package linear.simple;

public class BNode<T> {
    private T data;
    public BNode<T> next;

    public BNode(T data) {
        this.next = null;
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public BNode<T> getNext() {
        return this.next;
    }

    public void setNext(BNode<T> BNode) {
        this.next = BNode;
    }
}