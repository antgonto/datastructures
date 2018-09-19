package sort;

public class BubbleSort {
        public static void bubble_srt(int array[]) {
            int n = array.length;
            int k;
            for (int m = 0; m < n; m++) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (array[i] > array[k]) {
                        int temp;
                        temp = array[i];
                        array[i] = array[k];
                        array[k] = temp;
                    }
                }
                printNumbers(array);
            }
        }
        private static void swapNumbers(int i, int j, int[] array) {

        }

        private static void printNumbers(int[] input) {
            for (int i = 0; i < input.length; i++) {
                System.out.print(input[i] + ", ");
            }
            System.out.println("\n");
        }

        public static void main(String[] args) {
            int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
            bubble_srt(input);
        }
    }
