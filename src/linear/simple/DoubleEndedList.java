package linear.simple;

public class DoubleEndedList {
    private BNode head;
    private BNode last;
    private int size;

    public DoubleEndedList() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == this.last && this.last== null;
    }

    public int size() {
        return this.size;
    }
    public void insertFirst(Object data) {
        BNode newBNode = new BNode(data);
        if (this.isEmpty()) {
            this.head = this.last = newBNode;
        } else {
            newBNode.setNext(this.head);
            this.head = newBNode;
        }
        this.size++;
    }
    public void insertLast(Object data) {
        BNode newBNode = new BNode(data);
        if (this.isEmpty()) {
            this.head = this.last = newBNode;
        } else {
            this.last.setNext(newBNode);
            this.last = newBNode;
        }
        this.size++;
    }

    public void displayList() {
        BNode current = this.head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

}