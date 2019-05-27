package sorts;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Algorithms al = new Algorithms();
        int[][] a = Array.fillArrays();
        al.countingSort(a[0]);
        al.quickSort(a[1]);
        al.mergeSort(a[2]);
        al.insertionSort(a[3]);

        for(int i = 0; i < Variables.NUMBER_OF_ARRAYS; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
            System.out.println();
        }

    }
}