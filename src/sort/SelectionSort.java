package sort;

public class SelectionSort {
    public int[] doSelectionSort(int[] arr){

        for (int i = 0; i < arr.length - 1; i++){
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }

    public static void main(String a[]){
        SelectionSort ss = new SelectionSort();
        int[] arr1 = {1, 2, 3, 9, 7, 13, 6, 5, 12, 10};
        int[] arr2 = ss.doSelectionSort(arr1);
        for(int i:arr2){
            System.out.print(i);
            System.out.print(", ");
        }
    }
}