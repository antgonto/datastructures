package sort;

public class ShellSort {
    static void printArray(int arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public int sort(int arr[]){
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2){
            for (int i = gap; i < n; i += 1){
                int temp = arr[i];
                 int j = 0;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
        return 0;
    }

    public static void main(String args[]){
        int arr[] = {14, 7, 3, 12, 9, 11, 6, 2};
        System.out.println("Antes de ordenar");
        printArray(arr);

        ShellSort ob = new ShellSort();
        ob.sort(arr);

        System.out.println("Después de ordenar");
        printArray(arr);
    }
}