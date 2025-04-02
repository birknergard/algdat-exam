package Sorters;

import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter {
    private static void quickSortRec(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex){
        // If low and high is the same that means the partition has been partitioned down to one element, in which case we break the sort.
        if(higherBoundaryIndex - lowerBoundaryIndex == 1){
            return;
        }

        int pivotIndex = higherBoundaryIndex;

        double pivotValue = list.get(pivotIndex);
        System.out.printf("PivotValue: %.2f\n", pivotValue);
        int iteratorI = lowerBoundaryIndex;
        int iteratorJ = lowerBoundaryIndex;

        for(;iteratorJ < higherBoundaryIndex; iteratorJ++){
            if(list.get(iteratorJ) <= pivotValue){
                Collections.swap(list, iteratorJ, iteratorI);
                iteratorI++;
            }
            System.out.println("J:" + iteratorJ);
            System.out.println("I:" + iteratorI);
        }
        Collections.swap(list, iteratorI, pivotIndex);

        int newPivotIndex = iteratorI;
        /*
        quickSortRec(list, lowerBoundaryIndex, newPivotIndex - 1);
        quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex);
         */
    }

    public static int sort(LinkedList<Double> list){
        quickSortRec(list, 0, list.size() - 1);

        return 1;
    }

    public static void main(String[] args) {
        LinkedList<Double> testList = new LinkedList<>();
        // Generate a large randomized list for testing
        for(int i = 0; i < 10; i++){
            testList.push(Math.floor(Math.random() * 100000) / 100);
        }

        Collections.shuffle(testList);
        System.out.print("Before sort: ");
        System.out.println(testList);

        int iterations = sort(testList);
        System.out.print("After sort: ");
        System.out.println(testList);
        System.out.printf("Iteration count: %d\n", iterations);
    }
}
