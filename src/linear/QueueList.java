package linear;


import linear.simple.Node;

public class QueueList<T> {
        private Node<T> head = null;
        private Node<T> tail = null;

        public void enqueue(T item) {
            Node newNode = new Node(item);
            if (isEmpty()) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
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
            for (Node node = head; node != null; node = node.next) {
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
