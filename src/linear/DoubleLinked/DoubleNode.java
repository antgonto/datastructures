package linear.DoubleLinked;

public class DoubleBNode {

    private DoubleBNode prev;
    private DoubleBNode next;
    private char c;

    /**
     * Constructor with no arguments.
     */
    public DoubleBNode(){
        this( null, '\0', null );
    }

    /**
     * Constructor with a character
     * @param ch is a character for this BNode.
     */
    public DoubleBNode(char ch){
        this(null, ch, null);
    }

    /**
     * Constructor with three arguments
     * @param p is a pointer to a previous BNode.
     * @param ch is a character for this BNode.
     * @param n is a pointer to a next BNode.
     */
    public DoubleBNode(DoubleBNode p, char ch, DoubleBNode n){
        prev = p;
        next = n;
        c = ch;
    }

    /**
     * @return c a character for this BNode.
     */
    public char getC(){
        return c;
    }

    /**
     * @return a pointer to the next BNode or null if none exists
     */
    public DoubleBNode getNext(){
        return next;
    }

    /**
     * @return a pointer to the previous BNode or null if none exists
     */
    public DoubleBNode getPrev(){
        return prev;
    }

    /**
     * @param ch is assigned to this BNode
     */
    public void setC(char ch){
        c = ch;
    }

    /**
     * @param n
     */
    public void setNext(DoubleBNode n){
        next = n;
    }

    /**
     * @param p
     */
    public void setPrev(DoubleBNode p){
        prev = p;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return Character.toString(c);
    }
}