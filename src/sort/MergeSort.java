package sort;

public class MergeSort {
    private int[] numeros;
    private int[] auxiliar;

    private int longitud;

    public void sort(int[] valores) {
        this.numeros = valores;
        longitud = valores.length;
        this.auxiliar = new int[longitud];
        mergesort(0, longitud - 1);
        print();
    }

    private void print(){
        for(int i = 0; i < longitud; i++){
            System.out.print(this.numeros[i] + " ");
        }

    }

    private void mergesort(int primero, int ultimo) {
        if (primero < ultimo) {
            int mitad = primero + (ultimo - primero) / 2;
            mergesort(primero, mitad);
            mergesort(mitad + 1, ultimo);
            merge(primero, mitad, ultimo);
        }
    }

    private void merge(int primero, int mitad, int ultimo) {
        for (int i = primero; i <= ultimo; i++) {
            auxiliar[i] = numeros[i];
        }

        int i = primero;
        int j = mitad + 1;
        int k = primero;
        while (i <= mitad && j <= ultimo) {
            if (auxiliar[i] <= auxiliar[j]) {
                numeros[k] = auxiliar[i];
                i++;
            } else {
                numeros[k] = auxiliar[j];
                j++;
            }
            k++;
        }
        while (i <= mitad) {
            numeros[k] = auxiliar[i];
            k++;
            i++;
        }
     }
    public static void main(String[] args){
        MergeSort mergeSort = new MergeSort();
        int[] valores =  {12, 34, 54, 2, 3, 4, 21, 1};
        mergeSort.sort(valores);

    }
}
