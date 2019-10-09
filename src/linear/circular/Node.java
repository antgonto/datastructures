package linear.circular;

public class Node<T> {

    public T item;
    public Node<T> next;

    public Node(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Data Item = " + item;
    }

}