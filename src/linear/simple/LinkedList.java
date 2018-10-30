package linear.simple;

public class LinkedList<T>{
    private Node<T> head;
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
        Node<T> newBNode = new Node(data);
        newBNode.next = this.head;
        this.head = newBNode;
        this.size++;
    }
    public Node<T> deleteFirst() {
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp;
        } else {
            return null;
        }
    }

    public Node<T> getHead(){
        return this.head;
    }

    public void displayList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
    public Node find(String searchValue) {
        Node current = this.head;
        while (current != null) {
            if (current.getData().equals(searchValue)) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
    }
    public Node delete(String searchValue) {
        Node current = this.head;
        Node previous = this.head;

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

        int [] a = new int[10];
        a[0] = 10;
        a[1] = 8;

        a[0] = 11;
        for(int index = 0; index < a.length; index++){
            System.out.println(a[index]);
        }

        int[][] matriz = new int[3][5];
        matriz[0][0] = 10;
        matriz[0][1] = 11;
        matriz[0][2] = 12;
        matriz[0][3] = 13;
        matriz[0][4] = 14;

        for(int fila = 0; fila < matriz.length; fila++){
            for(int columna = 0; columna < matriz[0].length; columna++){
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }

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
        lList.displayList();

        System.out.println("Dato encontrado: " + lList.find("Dato5").getData());
        System.out.println("Dato borrado: " + lList.delete("Dato5").getData());
        lList.displayList();


        DoubleEndedList de = new DoubleEndedList();
        de.insertFirst("Dato1");
        de.insertFirst("Dato2");
        de.insertFirst("Dato3");
        de.displayList();



    }

}