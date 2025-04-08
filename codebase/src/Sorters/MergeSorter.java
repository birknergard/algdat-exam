package Sorters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeSorter implements Sorter{
    //Data fields
    private int logMerges;
    private int logOperations;

    public MergeSorter(){
       this.logMerges = 0;
       this.logOperations = 0;
    }

    public int getOperations() {
        return this.logOperations;
    }

    public int getMerges() {
        return this.logMerges;
    }

    public void sort(Double[] list, int flag) {
        int listLength = list.length;
        if (listLength < 2) {
            return;
        }

        int middle = listLength / 2;
        Double[] leftHalf = new Double[middle];
        Double[] rightHalf = new Double[listLength - middle];

        for (int i = 0; i < middle; i++) {
            leftHalf[i] = list[i];
        }

        for (int i = middle; i < listLength; i++) {
            rightHalf[i - middle] = list[i];
        }

        //Recursive calls for both halves
        sort(leftHalf, 0);
        sort(rightHalf, 0);

        //Merging the sorted halves with merge()
        merge(list, leftHalf, rightHalf);
    }

    //Merge function to combine sorted halves
    private void merge(Double[] list, Double[] leftHalf, Double[] rightHalf) {
        logMerges++;
        int i = 0, j = 0, k = 0;
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;


        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                list[k++] = leftHalf[i++];
            } else {
                list[k++] = rightHalf[j++];
            }
        }

        while (i < leftSize) {
            list[k++] = leftHalf[i++];
        }

        while (j < rightSize) {
            list[k++] = rightHalf[j++];
        }
    }

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
