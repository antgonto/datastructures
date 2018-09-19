package linear.simple;

public class DoubleEndedList {
    private Node head;
    private Node last;
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
        Node newNode = new Node(data);
        if (this.isEmpty()) {
            this.head = this.last = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size++;
    }
    public void insertLast(Object data) {
        Node newNode = new Node(data);
        if (this.isEmpty()) {
            this.head = this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
        }
        this.size++;
    }

    public void displayList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

}