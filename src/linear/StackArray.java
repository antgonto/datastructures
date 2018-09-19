package linear;

public class StackArray {
        private int maxSize;
        private Object[] stackArray;
        private int top = 0;

        public StackArray(int maxSize){
            this.maxSize = maxSize;
            stackArray = new Object[maxSize];
        }

        public void push(Object newObject){
            if (top < maxSize) {
                this.stackArray[++top] = newObject;
            } else {
                System.out.println("StackArray is full");
            }
        }

        public Object pop() {
            return this.stackArray[top--];
        }

        public Object peek() {
            return this.stackArray[top];
        }

        public boolean isEmpty(){
            return top == 0;
        }

        public static void main(String[] args){
            StackArray stackArray = new StackArray(10);
            stackArray.push("Dato1");
            stackArray.push("Dato2");
            stackArray.push("Dato3");

            while (!stackArray.isEmpty()){
                System.out.println(stackArray.pop());
            }

            System.out.println("------");
        }
}
