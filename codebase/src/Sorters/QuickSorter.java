package Sorters;

import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter {
    private void quickSortRec(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex){
        // If low and high is the same that means the partition has been partitioned down to one element, in which case we break the sort.
        if(lowerBoundaryIndex == higherBoundaryIndex){
            return;
        }

        double pivotValue = list.get(higherBoundaryIndex);
        int iteratorI = lowerBoundaryIndex;
        int iteratorJ = lowerBoundaryIndex;

        for(;iteratorJ < higherBoundaryIndex; iteratorJ++){
            if(list.get(iteratorJ) <= pivotValue){
                Collections.swap(list, iteratorJ, iteratorI);
                iteratorI++;
            }
        }

        int pivotIndex = iteratorI;

        quickSortRec(list, lowerBoundaryIndex, pivotIndex - 1);
        quickSortRec(list, pivotIndex + 1, higherBoundaryIndex);
    }

    public int sort(LinkedList<Double> list){
        quickSortRec(list, 0, list.size() - 1);

        return 1;
    }

    public static void main(String[] args) {

    }
}
