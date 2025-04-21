package Sorters;

import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter implements Sorter{
    private int logPartitions;
    private int logComparisons;
    private int logOperations;

    public QuickSorter(){
       this.logPartitions = 0;
       this.logOperations = 0;
       this.logComparisons = 0;
    }

    public int getOperations() {
        return this.logOperations;
    }

    public void reset(){
        this.logPartitions = 0;
        this.logOperations = 0;
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
        if(withRandomPivot){
                // with random pivot(index), it can not be first or last element
                int pivotIndex = (int) (Math.random() * (list.length - 1)) + 1;
                pivot = list[pivotIndex];
        } else {
            pivot = list[lowerBoundaryIndex];
        }

        int left = lowerBoundaryIndex - 1;
        int right = higherBoundaryIndex + 1;

        while(true){
            do{
                logComparisons++;
                left++;
            } while(list[left] < pivot);

            do {
                logComparisons++;
                right--;
            } while(list[right] > pivot);

            if(left >= right){
                logComparisons++;
                return right;
            }

            swap(list, left, right);
        }
    }

    // Lomuto partitioning
    private int partitionLom(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex){
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
        // TODO: breakpoint
        if(lowerBoundaryIndex >= higherBoundaryIndex || lowerBoundaryIndex < 0 ){
            return;
        }
        int newPivotIndex;
        switch (partitionMethod){
            // Lomuto, pivot is last element
            case 0:
                newPivotIndex = partitionLom(list, lowerBoundaryIndex, higherBoundaryIndex);
                //System.out.printf("Index of new pivot: %d\n", newPivotIndex);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex - 1, 0);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 0);
                break;

            // Hoare, pivot is first element
            case 1:
                newPivotIndex = partitionHoare(list, lowerBoundaryIndex, higherBoundaryIndex, false);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex, 1);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 1);
                break;

            // Hoare, first pivot is random(not first or last), then first element for each partition
            case 2:
                newPivotIndex = partitionHoare(list, lowerBoundaryIndex, higherBoundaryIndex, true);
                quickSortRec(list, lowerBoundaryIndex, newPivotIndex, 1);
                quickSortRec(list, newPivotIndex + 1, higherBoundaryIndex, 1);
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
