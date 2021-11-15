package ua.lviv.iot;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSelect {
    private static int partition(ArrayList<Hamster> arrayToSort,
                                 int low, int high) {
        int pivot = arrayToSort.get(high).consumption;
        int pivotLoc = low;
        for (int i = low; i <= high; i++) {
            if (arrayToSort.get(i).consumption < pivot) {
                Collections.swap(arrayToSort, i, pivotLoc);
                pivotLoc++;
            }
        }
        Collections.swap(arrayToSort, high, pivotLoc);

        return pivotLoc;
    }

    public static void kthSmallest(ArrayList<Hamster> inputArray, int k) {
        if (inputArray.size() ==1) {return;}
        kthSmallest(inputArray, 0, inputArray.size() - 1, k);
    }

    private static Hamster kthSmallest(ArrayList<Hamster> inputArray, int low,
                                  int high, int k) {
        int partition = partition(inputArray, low, high);
        if (partition == k - 1 ) {
            return inputArray.get(partition);
        } else if (partition < k - 1 ) {
            return kthSmallest(inputArray, partition + 1, high, k);
        } else {
            return kthSmallest(inputArray, low, partition - 1, k);
        }
    }
}
