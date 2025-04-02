package Sorters;
import java.util.LinkedList;
import java.util.Collections;

public class MergeSorter {

    public static void sort(LinkedList<Integer> list) {
        int listLength = list.size();
        if (listLength < 2) {
            return;
        }

        int middle = listLength / 2;
        LinkedList<Integer> leftHalf = new LinkedList<>();
        LinkedList<Integer> rightHalf = new LinkedList<>();

        for (int i = 0; i < middle; i++) {
            leftHalf.add(list.get(i));
        }

        for (int i = middle; i < listLength; i++) {
            rightHalf.add(list.get(i));
        }

        //Recursive calls for both halves
        sort(leftHalf);
        sort(rightHalf);

        //Merging the sorted halves
        list.clear();
        int i = 0, j = 0;

        while (i < leftHalf.size() && j < rightHalf.size()) {
            if (leftHalf.get(i) <= rightHalf.get(j)) {
                list.add(leftHalf.get(i));
                i++;
            } else {
                list.add(rightHalf.get(j));
                j++;
            }
        }

        //Add the remaining elements from the left half
        while (i < leftHalf.size()) {
            list.add(leftHalf.get(i));
            i++;
        }

        //Add remaining elements from the right half
        while (j < rightHalf.size()) {
            list.add(rightHalf.get(j));
            j++;
        }
    }

    public static void main(String[] args) {

        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            testList.add(i);
        }
        Collections.shuffle(testList);

        System.out.println("\nBefore sorting with MergeSort: " + testList);
        long startTimeOpt = System.nanoTime();
        sort(testList);
        long endTimeOpt = System.nanoTime();
        long timeOpt = endTimeOpt - startTimeOpt;
        System.out.println("After sorting with MergeSort: " + testList);
        System.out.println("\nTime taken with MergeSort: " + timeOpt + " nanoseconds");
    }
}
