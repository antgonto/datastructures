package linear.DoubleLinked;

public class DoublyLinkedList {

    private DoubleNode head;
    private DoubleNode tail;

    /**
     * Constructs a new DoublyLinkedList object with head and tail as null.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Returns true if the list is empty false otherwise
     * @return true if the list empty false otherwise
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Add a character BNode containing the character c to the end of the linked list. This routine does not require a search.
     * @param c a single character
     */
    public void addCharAtEnd(char c){
        DoubleNode n = new DoubleNode(tail, c, null);
        if(isEmpty()){
            head = tail = n;
        }else{
            tail.setNext(n);
            tail = n;
        }
    }

    /**
     * Add a character BNode containing the character c to the front of the linked list. No search is required.
     * @param c a single character
     */
    public void addCharAtFront(char c){
        DoubleNode n = new DoubleNode(null, c, head);
        if(isEmpty()){
            head = tail = n;
        }else{
            head.setPrev(n);
            head = n;
        }
    }

    /**
     * Remove and return the character at the front of the doubly linked list.
     * @return the character at the front precondition: the list is not empty
     */
    public char removeCharFromFront(){
        char ch = head.getC();
        if(!isEmpty()){
            if(head == tail){
                head = tail = null;
            }else{
                head = head.getNext();
                head.setPrev(null);
            }
        }
        return ch;
    }

    /**
     * Remove and return the character at the end of the doubly linked list. No searching is required.
     * @return the character at the end precondition: the list is not empty
     */
    public char removeCharAtEnd(){
        char ch = tail.getC();

        if(!isEmpty()){
            if(head == tail){
                head = tail = null;
            }else{
                tail = tail.getPrev();
                tail.setNext(null);
            }
        }

        return ch;
    }

    /**
     * Counts the number of BNodes in the list. We are not maintaining a counter so a search is required.
     * @return the number of BNodes in the doubly linked list between head and tail inclusive
     */
    public int countBNodes(){
        DoubleNode cn = head;
        int count = 0;
        while(cn != null){
            count++;
            cn = cn.getNext();
        }
        return count;
    }

    /**
     * Deletes the first occurence of the character c from the list. If the character c is not in the list then no modifications are made. This method needs to search the list.
     * @param c the character to be searched for.
     * @return true if a deletion occurred and false otherwise
     */
    public boolean deleteChar(char c){
        DoubleNode cn = head;
        while(cn.getC() != c){
            cn = cn.getNext();
        }

        if(cn != null){
            if(cn.getPrev() == null){
                head = cn.getNext();
                if(!isEmpty()){
                    head.setPrev(null);
                }
            }else if(cn.getNext() == null){
                tail = cn.getPrev();
                if(!isEmpty()){
                    tail.setNext(null);
                }
            }else{
                cn.getPrev().setNext(cn.getNext());
                cn.getNext().setPrev(cn.getPrev());
            }
        }

        return cn != null;
    }

    /**
     * Returns the list as a String. The class DoubleBNode has a toString that will be called from this toString. The String returned must be presented clearly. Null pointers must be represented differently than non-null pointers.
     * @see java.lang.Object#toString()
     * @return a String containing the characters in the list
     */
    public String toString(){
        DoubleNode cn = head;
        String str = "";
        while(cn != null){
            str += cn.getC();
            //System.out.print(cn.getC());
            cn = cn.getNext();
        }

        return str;
    }

    /**
     *Reverse the list. a -> b -> c becomes c -> b -> a
     */
    public void reverse(){
        DoubleNode cn = head;
        DoubleNode tmp = null;
        while(cn != null){
            tmp = cn.getNext();
            cn.setNext(cn.getPrev());
            cn.setPrev(tmp);

            cn = cn.getPrev();
        }
        tmp = head;
        head = tail;
        tail = tmp;
        head.setPrev(null);
        tail.setNext(null);
    }

    /**
     * Test driver for DoublyLinkedList
     */
    public static void main(String a[]) {

        DoublyLinkedList list = new DoublyLinkedList();
        list.addCharAtEnd('H');
        list.addCharAtEnd('e');
        list.addCharAtEnd('l');
        list.addCharAtEnd('l');
        list.addCharAtEnd('o');
        System.out.println(list);

        System.out.println("Deleting l");
        list.deleteChar('l');
        System.out.println(list);

        System.out.println("Deleting H");
        list.deleteChar('H');
        System.out.println(list);

        System.out.println("Deleting o");
        list.deleteChar('o');
        System.out.println(list);

        System.out.println("Deleting e");
        list.deleteChar('e');
        System.out.println(list);

        System.out.println("Deleting l");
        list.deleteChar('l');
        System.out.println(list);

        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');
        System.out.println(list);

        System.out.println(list.countBNodes());

        System.out.println("Popping everything");
        while(!list.isEmpty()){
            System.out.println(list.removeCharFromFront());
        }

        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');

        System.out.println("Popping everything from the end");
        while(!list.isEmpty()){
            System.out.println(list.removeCharAtEnd());
        }
        System.out.println(list.countBNodes());

        list.addCharAtEnd('o');
        list.addCharAtEnd('l');
        list.addCharAtEnd('l');
        list.addCharAtEnd('e');
        list.addCharAtEnd('H');

        list.reverse();
        System.out.println(list);

        list.reverse();
        System.out.println(list);

    }
}