package linear.simple;

public class Node<T> {
    private T data;
    public Node<T> next;

    public Node(T data) {
        this.next = null;
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> BNode) {
        this.next = BNode;
    }
}