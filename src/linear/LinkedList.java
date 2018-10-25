package linear;

public class LinkedList {
    private BNode head;
    private int size;
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    public boolean isEmpty() {
        return this.head == null;
    }
    public int size() {
        return this.size;
    }
    public void insertFirst(Object data) {
        BNode newBNode = new BNode(data);
        newBNode.next = this.head;
        this.head = newBNode;
        this.size++;
    }
    public BNode deleteFirst() {
        if (this.head != null) {
            BNode temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp;
        } else {
            return null;
        }
    }
    public void displayList() {
        BNode current = this.head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
    public BNode find(String searchValue) {
        BNode current = this.head;
        while (current != null) {
            if (current.getData().equals(searchValue)) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
    }
    public BNode delete(String searchValue) {
        BNode current = this.head;
        BNode previous = this.head;

        while (current != null) {
            if (current.getData().equals(searchValue)) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return current;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    public static void main(String[] args){
        LinkedList lList = new LinkedList();
        lList.insertFirst("Dato1");
        lList.insertFirst("Dato2");
        lList.insertFirst("Dato3");
        lList.displayList();
        lList.deleteFirst();
        System.out.println();
        lList.displayList();
        lList.insertFirst("Dato4");
        lList.insertFirst("Dato5");
        lList.insertFirst("Dato6");
        System.out.println("Dato encontrado: " + lList.find("Dato5").getData());

        System.out.println("Dato borrado: " + lList.delete("Dato5").getData());
        lList.displayList();
    }

}