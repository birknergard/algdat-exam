package Sorters;

import javax.xml.transform.Source;
import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter {
    // Hoare partitioning
    private static int partitionHoare(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex){
        // Sets pivot as low
        double pivot = list.get(lowerBoundaryIndex);
        int left = lowerBoundaryIndex - 1;
        int right = higherBoundaryIndex + 1;

        while(true){
            do{
                left++;
            } while(list.get(left) < pivot);

            do {
                right--;
            } while(list.get(right) > pivot);

            if(left >= right){
                return right;
            }

            Collections.swap(list, left, right);
        }
    }

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

    private static void quickSortRec(LinkedList<Double> list, int lowerBoundaryIndex, int higherBoundaryIndex, int partitionMethod){
        // TODO: breakpoint
        if(lowerBoundaryIndex >= higherBoundaryIndex || lowerBoundaryIndex < 0 ){
            return;
        }
        int newPivotIndex;
        switch (partitionMethod){
            // Lomuto
            case 0:
                newPivotIndex = partitionLom(list, lowerBoundaryIndex, higherBoundaryIndex);
                System.out.printf("Index of new pivot: %d\n", newPivotIndex);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex - 1, partitionMethod);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, partitionMethod);
                break;

            case 1:
                newPivotIndex = partitionHoare(list, lowerBoundaryIndex, higherBoundaryIndex);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex, partitionMethod);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, partitionMethod);
                break;
        }
    }

    public static int sort(LinkedList<Double> list){
        quickSortRec(list, 0, list.size() - 1, 1);

        return 1;
    }

    public static void main(String[] args) {
        LinkedList<Double> testList = new LinkedList<>();
        // Generate a large randomized list for testing
        for(int i = 0; i < 100; i++){
            testList.push(Math.floor(Math.random() * 100000) / 100);
        }
        /*
        testList.clear();
        testList.add(1.0);
        testList.add(2.0);
        testList.add(3.0);
        testList.add(4.0);
        testList.add(5.0);
        testList.add(6.0);
        testList.add(7.0);
        testList.add(8.0);
         */

        Collections.shuffle(testList);
        System.out.print("Before sort: ");
        //System.out.println(testList);

        int iterations = sort(testList);
        System.out.print("After sort: ");
        //System.out.println(testList);
        System.out.printf("Iteration count: %d\n", iterations);
    }
}
