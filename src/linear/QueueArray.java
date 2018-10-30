package linear;

import linear.simple.Node;

public class QueueArray {
        private int maxSize;
        private Object[] queueArray;
        private int front = 0;
        private int rear = 0;
        private int nItems = 0;

        public QueueArray(int maxSize){
            this.maxSize = maxSize;
            queueArray = new Object[maxSize];
        }

        public void enqueue(Object element) {


            System.out.println("Front: " + (this.front + 1) % this.maxSize);
            this.queueArray[this.front] = element;
            this.front = (this.front + 1) % this.maxSize;
            this.nItems++;
        }

        public Object dequeue() {
            Object toReturn = this.queueArray[this.rear];
            this.queueArray[this.rear] = null;
            System.out.println("Rear: " + (this.rear + 1) % this.maxSize);
            this.rear = (this.rear + 1) % this.maxSize;
            this.nItems--;
            return toReturn;
        }

        public boolean isEmpty(){
            return nItems == 0;
        }

    public static void main(String[] args){
        QueueArray queueArray = new QueueArray(10);

        queueArray.enqueue("Dato1");
        queueArray.enqueue("Dato2");
        queueArray.enqueue("Dato3");
        queueArray.enqueue("Dato4");
        while (!queueArray.isEmpty()){
            System.out.println(queueArray.dequeue());
        }
        queueArray.enqueue("Dato5");
        while (!queueArray.isEmpty()){
            System.out.println(queueArray.dequeue());
        }
        System.out.println("------");
    }

}