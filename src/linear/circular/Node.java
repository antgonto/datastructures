package linear.circular;

public class BNode<T> {

    T item;
    BNode<T> next;

    public BNode(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Data Item = " + item;
    }

}