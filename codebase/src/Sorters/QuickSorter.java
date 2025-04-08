package Sorters;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class QuickSorter implements Sorter{

    int partitions;
    int operations;

    public QuickSorter(){
       partitions = 0;
       operations = 0;
    }

    private void swap(Double[] list, int indexA, int indexB){
        double temp = list[indexA];
        list[indexA] = list[indexB];
        list[indexB] = temp;
    }

    // Hoare partitioning
    private int partitionHoare(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex, boolean withRandomIndex){
        double pivot;
        if(withRandomIndex) {
            // with random pivot(index), it can not be first or last element
            int pivotIndex = (int) Math.floor(Math.random() * list.length - 1) + 1;
            pivot = list[pivotIndex];

        } else {
            // Sets pivot as low
            pivot = list[lowerBoundaryIndex];
        }
        int left = lowerBoundaryIndex - 1;
        int right = higherBoundaryIndex + 1;

        while(true){
            do{
                left++;
            } while(list[left] < pivot);

            do {
                right--;
            } while(list[right] > pivot);

            if(left >= right){
                return right;
            }

            swap(list, left, right);
        }
    }

    // Lomuto partitioning
    private int partitionLom(Double[] list, int lowerBoundaryIndex, int higherBoundaryIndex){
        double pivotValue = list[higherBoundaryIndex];
        System.out.printf("Pivot for partition: %.2f\n", pivotValue);

        //System.out.printf("PivotValue: %.2f\n", pivotValue);
        int iteratorI = lowerBoundaryIndex;
        for(int iteratorJ = lowerBoundaryIndex ; iteratorJ <= higherBoundaryIndex - 1; iteratorJ++){
            if(list[iteratorJ] <= pivotValue){
                swap(list, iteratorJ, iteratorI);
                iteratorI++;
            }
        }
        swap(list, iteratorI, higherBoundaryIndex);
        //System.out.println("Current List:" + list);

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
                System.out.printf("Index of new pivot: %d\n", newPivotIndex);
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

            default:
                System.out.println("Incorrect sort flag for quicksort.");
                break;
        }
    }

    public void sort(Double[] list, int flag){
        quickSortRec(list, 0, list.length - 1, flag);
    }

    /*
    public static void main(String[] args) {
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

        int iterations = sort(array);
        System.out.print("After sort: ");
        //System.out.println(Arrays.toString(array));
        System.out.printf("Iteration count: %d\n", iterations);
    }
       */
}
