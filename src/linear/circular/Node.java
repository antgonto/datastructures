package linear.circular;

public class Node<T> {

    T item;
    Node<T> next;

    public Node(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Data Item = " + item;
    }

}