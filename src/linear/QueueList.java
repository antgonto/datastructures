package linear;


import linear.simple.BNode;

public class QueueList<T> {
        private BNode<T> head = null;
        private BNode<T> tail = null;

        public void enqueue(T item) {
            BNode newBNode = new BNode(item);
            if (isEmpty()) {
                head = newBNode;
            } else {
                tail.next = newBNode;
            }
            tail = newBNode;
        }

        public T dequeue() {
            if (isEmpty()) {
                return null;
            }
            T item = head.getData();
            if (tail == head) {
                tail = null;
            }
            head = head.next;
            return item;
        }

        public T peek() {
            if (head == null) {
                return null;
            }
            return head.getData();
        }

        public int size() {
            int count = 0;
            for (BNode BNode = head; BNode != null; BNode = BNode.next) {
                count++;
            }
            return count;
        }

        public boolean isEmpty() {
            return head == null;
        }

    public static void main(String[] args){
        QueueList queueList = new QueueList();

        queueList.enqueue("Dato1");
        queueList.enqueue("Dato2");
        queueList.enqueue("Dato3");
        queueList.enqueue("Dato4");
        while (!queueList.isEmpty()){
            System.out.println(queueList.dequeue());
        }
        queueList.enqueue("Dato5");
        while (!queueList.isEmpty()){
            System.out.println(queueList.dequeue());
        }
        System.out.println("------");
    }

}
