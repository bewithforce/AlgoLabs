package sorts;

class Algorithms {
    void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    void mergeSort(int[] list) {
        merge(list, 0, list.length - 1);
    }

    void quickSort(int[] list, int first, int last) {
        if(first >= last){
            return;
        }
        int pivotIndex = (first + last) / 2;
        int i = first, j = last;
        while (i < j) {
            while (i < pivotIndex && (list[i] <= list[pivotIndex])) {
                i++;
            }
            while (j > pivotIndex && (list[pivotIndex] <= list[j])) {
                j--;
            }
            if (i < j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                if (i == pivotIndex) {
                    pivotIndex = j;
                } else if (j == pivotIndex) {
                    pivotIndex = i;
                }
            }
        }
        quickSort(list, first, pivotIndex);
        quickSort(list, pivotIndex + 1, last);
    }


    void insertionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int currentElement = list[i];
            int j = i - 1;
            while (j >= 0 && list[j] > currentElement) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = currentElement;
        }
    }

    void countingSort(int[] list){
        int max = list[0];
        for(int i = 1; i < list.length; i++){
            if(list[i] > max){
                max = list[i];
            }
        }
        int[] C = new int[max + 1];
        for(int i = 0; i < max; i++) {
            C[i] = 0;
        }
        for(int i : list) {
            C[i]++;
        }
        int b = 0;
        for(int i = 0; i < max; i++) {
            for(int j = 0; j < C[i]; j++){
                list[b++] = i;
            }
        }
    }

    void merge(int arr[], int l, int m, int r) {
        int k = l;
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i = 0; i < n1; i++)
            left[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    void merge(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            merge(arr, l, m);
            merge(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
}
