package sort;

public class InsertionSort {

    void sort(int arr[]){
        int n = arr.length;
        for (int i=1; i<n; ++i){
            int key = arr[i];
            int inicio = i-1;
            while (inicio>=0 && arr[inicio] > key){
                arr[inicio+1] = arr[inicio];
                inicio = inicio-1;
            }
            arr[inicio+1] = key;
            printArray(arr, inicio, i + 1);
        }
    }

    public static int[] doInsertionSort(int[] input){
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j-1] > input[j]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                    printArray(input, j-1, j);
                }
            }

        }
        return input;
    }

    static void printArray(int arr[], int i1, int i2){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            if(i == i1)
                System.out.print(arr[i] + " " + i1 + "-" + i2 + " ");
            else System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]){
        int arr[] = {14, 33, 27, 10, 35, 19, 42, 44};

        InsertionSort ob = new InsertionSort();
        printArray(arr, 0, 0);
        System.out.println("----------------------------");
        ob.doInsertionSort(arr);


    }



}