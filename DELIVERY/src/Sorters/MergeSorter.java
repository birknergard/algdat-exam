package Sorters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * CREATED BY CANDIDATE 119
 * */
public class MergeSorter implements Sorter{
    // For logging merges
    private int logMerges;

    public MergeSorter(){
       this.logMerges = 0;
    }

    public void reset(){
        this.logMerges = 0;
    }

    public int getMerges() {
        return this.logMerges;
    }

    // Merge function to combine sorted halves
    private void merge(Double[] list, Double[] leftHalf, Double[] rightHalf) {
        logMerges++; // For logging

        // Indexes for leftHalf(i), rightHalf(j), list(k)
        int i = 0, j = 0, k = 0;
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        // Compare elements from both halves and place smaller one in list
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                list[k++] = leftHalf[i++]; // Take an element from leftHalf
            } else {
                list[k++] = rightHalf[j++]; // Take an element from rightHalf
            }
        }

        // Copy remaining elements from leftHalf (if any)
        while (i < leftSize) {
            list[k++] = leftHalf[i++];
        }

        // Copy remaining elements from rightHalf (if any)
        while (j < rightSize) {
            list[k++] = rightHalf[j++];
        }
    }

    // Main sorting method
    public void sort(Double[] list, int flag) {
        int listLength = list.length;

        //If list has fewer than 2 elements, it is already sorted
        if (listLength < 2) {
            return;
        }

        // Find the middle index and split the list into two halves
        int middle = listLength / 2;

        // Create left sublist
        Double[] leftHalf = new Double[middle];
        for(int i = 0; i < middle; i++) {
            leftHalf[i] = list[i];
        }

        // Create right sublist
        Double[] rightHalf = new Double[listLength - middle];
        for(int i = middle; i < listLength; i++) {
            rightHalf[i - middle] = list[i];
        }

        //Recursive calls for both halves
        sort(leftHalf, 0);
        sort(rightHalf, 0);

        //Merging the sorted halves with merge()
        merge(list, leftHalf, rightHalf);
    }

    // For debugging sorting method
    public void internalTest(String[] args) {
        Double[] testList = new Double[10];
        for (int i = 0; i <= 9; i++) {
            testList[i] = (double) (i + 1);
        }

        List<Double> list = Arrays.asList(testList);
        Collections.shuffle(list);
        testList = list.toArray(new Double[0]);

        System.out.println("Before sorting: ");
        for (double num : list) {
            System.out.print(num + ", ");
        }
        System.out.println("\n");

        long startTimeOpt = System.nanoTime();
        sort(testList, 0);
        long endTimeOpt = System.nanoTime();
        long timeOpt = endTimeOpt - startTimeOpt;

        System.out.println("After sorting with MergeSort: ");
        for (double num : testList) {
            System.out.print(num + ", ");
        }
        System.out.println("\nTime taken with MergeSort: " + timeOpt + " nanoseconds");
    }
}
