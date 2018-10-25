package linear;

import linear.simple.LinkedList;
import linear.simple.BNode;

public class StackList<T> {
    private LinkedList stackList = new LinkedList();

    public void push(T newElement) {
        this.stackList.insertFirst(newElement);
    }

    public BNode<T> pop() {
        return this.stackList.deleteFirst();
    }

    public BNode<T> peek() {
        return this.stackList.getHead();
    }

    public boolean isEmpty(){
        return this.stackList.isEmpty();
    }

    public static void main(String[] args){
        StackList stackList = new StackList();
        stackList.push("Dato1");
        stackList.push("Dato2");
        stackList.push("Dato3");

        while (!stackList.isEmpty()){
            System.out.println(stackList.pop().getData());
        }

        System.out.println("------");
    }

}
