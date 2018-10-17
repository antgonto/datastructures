package search;

public class BinarySearch {
    int[] data = {20, 14, 54,90,99};
    int size = data.length;

    public boolean search(int key){
        int low = 0;
        int high = size - 1;
        while(high >= low) {
            int middle = (low + high) / 2;
            if(data[middle] == key) {
                return true;
            }
            if(data[middle] < key) {
                low = middle + 1;
            }
            if(data[middle] > key) {
                high = middle - 1;
            }
        }
        return false;
    }

    public static void main(String[] args){
        BinarySearch bs = new BinarySearch();
        int key = 99;
        System.out.println("El número " + key + " se encuentra en el array: " + bs.search(key));
    }
}
