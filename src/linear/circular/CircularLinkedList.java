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
        Node<E> newNode = new Node<E>(value);
        if (null == head) {
            head = newNode;
        } else {
            Node<E> temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        newNode.next = head;
        size++;
    }

    public void insertAtPosition(E value, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position is Invalid");
        }
        /* Conditions check passed, let's insert the node */
        Node<E> newNode = new Node<E>(value);
        Node<E> tempNode = head;
        Node<E> prevNode = null;
        for (int i = 0; i < position; i++) {
            if (tempNode.next == head) {
                break;
            }
            prevNode = tempNode;
            tempNode = tempNode.next;
        }
        prevNode.next = newNode;
        newNode.next = tempNode;
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
                System.out.println("Data at Node = " + temp.item);
                temp = temp.next;
            }
            System.out.println("Data at Node = " + temp.item);
        }
        System.out.println();
    }

    public static void main(String[] args){
        CircularLinkedList cll = new CircularLinkedList();

    }
}