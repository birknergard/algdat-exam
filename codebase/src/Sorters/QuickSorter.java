package Sorters;

import javax.xml.transform.Source;
import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter {
    // Lomuto partitioning
    private static int partitionLom(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex){
        double pivotValue = list.get(higherBoundaryIndex);
        System.out.printf("Pivot for partition: %.2f\n", pivotValue);

        //System.out.printf("PivotValue: %.2f\n", pivotValue);
        int iteratorI = lowerBoundaryIndex;
        for(int iteratorJ = lowerBoundaryIndex ; iteratorJ <= higherBoundaryIndex - 1; iteratorJ++){
            if(list.get(iteratorJ) <= pivotValue){
                Collections.swap(list, iteratorJ, iteratorI);
                iteratorI++;
            }
        }
        Collections.swap(list, iteratorI, higherBoundaryIndex);
        //System.out.println("Current List:" + list);

        // Returns the new pivot index
        return iteratorI;
    }

    private static void quickSortRec(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex){
        // TODO: breakpoint
        if(lowerBoundaryIndex >= higherBoundaryIndex || lowerBoundaryIndex < 0 ){
            return;
        }

        int newPivotIndex = partitionLom(list, lowerBoundaryIndex, higherBoundaryIndex);
        System.out.printf("Index of new pivot: %d\n", newPivotIndex);
        quickSortRec(list, lowerBoundaryIndex, newPivotIndex - 1);
        quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex);
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
