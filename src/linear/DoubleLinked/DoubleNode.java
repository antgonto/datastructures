package linear.DoubleLinked;

public class DoubleNode {

    private DoubleNode prev;
    private DoubleNode next;
    private char c;

    /**
     * Constructor with no arguments.
     */
    public DoubleNode(){
        this( null, '\0', null );
    }

    /**
     * Constructor with a character
     * @param ch is a character for this node.
     */
    public DoubleNode(char ch){
        this(null, ch, null);
    }

    /**
     * Constructor with three arguments
     * @param p is a pointer to a previous node.
     * @param ch is a character for this node.
     * @param n is a pointer to a next node.
     */
    public DoubleNode(DoubleNode p, char ch, DoubleNode n){
        prev = p;
        next = n;
        c = ch;
    }

    /**
     * @return c a character for this node.
     */
    public char getC(){
        return c;
    }

    /**
     * @return a pointer to the next node or null if none exists
     */
    public DoubleNode getNext(){
        return next;
    }

    /**
     * @return a pointer to the previous node or null if none exists
     */
    public DoubleNode getPrev(){
        return prev;
    }

    /**
     * @param ch is assigned to this node
     */
    public void setC(char ch){
        c = ch;
    }

    /**
     * @param n
     */
    public void setNext(DoubleNode n){
        next = n;
    }

    /**
     * @param p
     */
    public void setPrev(DoubleNode p){
        prev = p;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return Character.toString(c);
    }
}