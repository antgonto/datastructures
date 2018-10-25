package linear;

public class BNode {
    private Object data;
    public BNode next;

    public BNode(Object data) {
        this.next = null;
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public BNode getNext() {
        return this.next;
    }

    public void setNext(BNode BNode) {
        this.next = BNode;
    }
}