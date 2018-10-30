package linear.circular;

public class CircularLinkedList<E> {

    private Node<E> head;
    private int size = 0;

    public void insertAtBeginning(E value) {
        Node<E> newNode = new Node<E>(value);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<E> temp = head;
            newNode.next = temp;
            head = newNode;
        }
        size++;
    }

    public void insertAtTail(E value) {
        Node<E> newBNode = new Node<E>(value);
        if (null == head) {
            head = newBNode;
        } else {
            Node<E> temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newBNode;
        }
        newBNode.next = head;
        size++;
    }

    public void insertAtPosition(E value, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position is Invalid");
        }
        /* Conditions check passed, let's insert the BNode */
        Node<E> newBNode = new Node<E>(value);
        Node<E> tempBNode = head;
        Node<E> prevBNode = null;
        for (int i = 0; i < position; i++) {
            if (tempBNode.next == head) {
                break;
            }
            prevBNode = tempBNode;
            tempBNode = tempBNode.next;
        }
        prevBNode.next = newBNode;
        newBNode.next = tempBNode;
        size++;
    }

    public void deleteFromBeginning() {
        Node<E> temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = head.next;
        head = head.next;
        size--;
    }

    public void deleteFromPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Position is Invalid");
        }
        Node<E> current = head, previous = head;
        for (int i = 0; i < position; i++) {
            if (current.next == head) {
                break;
            }
            previous = current;
            current = current.next;
        }
        if (position == 0) {
            deleteFromBeginning();
        } else {
            previous.next = current.next;
        }
        size--;
    }

    public Node<E> searchByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is Invalid");
        }
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public Node<E> searchByValue(E value) {
        Node<E> temp = head;
        while (null != temp && temp.item != value) {
            temp = temp.next;
        }
        if (temp.item == value) {
            return temp;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        if (head == null) {
            System.out.println("List is Empty !!");
        } else {
            Node<E> temp = head;
            while (temp.next != head) {
                System.out.println("Data at BNode = " + temp.item);
                temp = temp.next;
            }
            System.out.println("Data at BNode = " + temp.item);
        }
        System.out.println();
    }

    public static void main(String[] args){
        CircularLinkedList cll = new CircularLinkedList();

    }
}