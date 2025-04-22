package Sorters;

import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter implements Sorter{
    // Logging variables
    private int logPartitions;
    private int logComparisons;

    public QuickSorter(){
       this.logPartitions = 0;
       this.logComparisons = 0;
    }

    public void reset(){
        this.logPartitions = 0;
        this.logComparisons = 0;
    }

    public int getComparisons() {
        return this.logComparisons;
    }

    public int getPartitions() {
        return this.logPartitions;
    }

    // 3 operations
    private void swap(Double[] list, int indexA, int indexB){
        double temp = list[indexA];
        list[indexA] = list[indexB];
        list[indexB] = temp;
    }

    // Use boolean to choose whether or not to use a random pivot
    private int partitionHoare(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex, boolean withRandomPivot){
        logPartitions++;
        double pivot;

        // If we select to use random pivot (task c)
        if(withRandomPivot){
                // with random pivot(index), it can not be first or last element
                int pivotIndex = (int) (Math.random() * (higherBoundaryIndex - lowerBoundaryIndex)) + lowerBoundaryIndex;
                pivot = list[pivotIndex];
        } else {
            // Else we use the first element in the subarray
            pivot = list[lowerBoundaryIndex];
        }

        /* Starts one index out of bounds to prevent ignoring of elements in
           this particular implementation */
        int left = lowerBoundaryIndex - 1;
        int right = higherBoundaryIndex + 1;

        // Finds two elements which are on the wrong side of the pivot and swaps them to the correct side
        while(true){
            // Finds elements larger than the pivot on the left side
            do{
                logComparisons++;
                left++;
            } while(list[left] < pivot);

            // And elements smaller than the pivot on the right side
            do {
                logComparisons++;
                right--;
            } while(list[right] > pivot);

            // Ends loop if every element has been checked
            if(left >= right){
                logComparisons++;
                return right;
            }

            // Swaps the elements to the correct side
            swap(list, left, right);
        }
    }

    // Lomuto partitioning
    private int partitionLom(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex){
        // For logging
        logPartitions++;

        double pivotValue = list[higherBoundaryIndex];

        int iteratorI = lowerBoundaryIndex;
        for(int iteratorJ = lowerBoundaryIndex ; iteratorJ <= higherBoundaryIndex - 1; iteratorJ++){
            logComparisons++;
            if(list[iteratorJ] <= pivotValue){
                swap(list, iteratorJ, iteratorI);
                iteratorI++;
            }
        }
        swap(list, iteratorI, higherBoundaryIndex);

        // Returns the new pivot index
        return iteratorI;
    }

    private void quickSortRec(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex, int partitionMethod){
        // Breakpoint for the recursive method
        if(lowerBoundaryIndex >= higherBoundaryIndex || lowerBoundaryIndex < 0){
            return;
        }

        // Stores the new pivot for the partition
        int newPivotIndex;

        switch (partitionMethod){
            // Task a: Lomuto, pivot is last element
            case 0:
                newPivotIndex = partitionLom(list, lowerBoundaryIndex, higherBoundaryIndex);
                //System.out.printf("Index of new pivot: %d\n", newPivotIndex);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex - 1, 0);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 0);
                break;

            // Task b: Hoare, pivot is first element
            case 1:
                newPivotIndex = partitionHoare(list, lowerBoundaryIndex, higherBoundaryIndex, false);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex, 1);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 1);
                break;

            // Task c: Hoare, pivot is random
            case 2:
                newPivotIndex = partitionHoare(list, lowerBoundaryIndex, higherBoundaryIndex, true);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex, 2);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 2);
                break;

            // Logging for misuse
            default:
                System.out.println("Incorrect sort flag for quicksort.");
                break;
        }
    }

    public void sort(Double[] list, int flag){
        // Only runs if list is big enough
        if(list.length > 2){
            quickSortRec(list, 0, list.length - 1, flag);
        }
    }

    // For internal testing
    public void internalTest() {
        LinkedList<Double> testList = new LinkedList<>();
        // Generate a large randomized list for testing
        for(int i = 0; i < 100; i++){
            testList.push(Math.floor(Math.random() * 100000) / 100);
        }
        testList.clear();
        testList.add(1.0);
        testList.add(2.0);
        testList.add(3.0);
        testList.add(4.0);
        testList.add(5.0);
        testList.add(6.0);
        testList.add(7.0);
        testList.add(8.0);
        Collections.shuffle(testList);
        Double[] array = testList.toArray(new Double[0]);
        System.out.print("Before sort: ");
        //System.out.println(testList);
        sort(array, 0);
        System.out.print("After sort: ");
        //System.out.println(Arrays.toString(array));
    }
}
