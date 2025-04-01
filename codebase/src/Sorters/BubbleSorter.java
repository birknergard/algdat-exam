package Sorters;
import java.util.Collections;
import java.util.LinkedList;

public class BubbleSorter {

    public static void sort(LinkedList<Integer> list) {
        int listLength = list.size();
        int swaps = 0;
        int passes = 0;

        //Sorting
        for (int i = 0; i < listLength - 1; i++) {
            passes++;
            for (int j = 0; j < listLength - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    //Swapping them numbers
                    int temporary = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temporary);
                    swaps++;
                }
            }
        }
        System.out.println("Sorted list using a non-optimised BubbleSort: " + list);
        System.out.println("Number of swaps: " + swaps);
        System.out.println("Number of passes: " + passes);
    }


    public static void optimisedSort(LinkedList<Integer> list) {
        int listLength = list.size();
        boolean swapped = false;
        int swaps = 0;
        int passes = 0;

        //Sorting
        for (int i = 0; i < listLength - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < listLength - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    //Swap em!
                    int temporary = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temporary);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.println("Sorted list using an optimised BubbleSort: " + list);
        System.out.println("Number of swaps: " + swaps);
        System.out.println("Number of passes: " + passes);
    }


    public static void main(String[] args) {
        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            testList.add(i);
        }

        Collections.shuffle(testList);

        //Non-optimised version
        System.out.println("Before sorting with non-optimised BubbleSort: " + testList);
        long startTimeNoOpt = System.nanoTime();
        sort(new LinkedList<>(testList)); //Bruker en kopi for å holde på den originale listen
        long endTimeNoOpt = System.nanoTime();
        long timeNoOpt = endTimeNoOpt - startTimeNoOpt;
        System.out.println("Time taken with a non-optimised BubbleSort: " + timeNoOpt + " nanoseconds");

        //Optimised version
        System.out.println("\nBefore sorting with an optimised BubbleSort: " + testList);
        long startTimeOpt = System.nanoTime();
        optimisedSort(new LinkedList<>(testList));
        long endTimeOpt = System.nanoTime();
        long timeOpt = endTimeOpt - startTimeOpt;
        System.out.println("Time taken with an optimised BubbleSort: " + timeOpt + " nanoseconds");

        //Calculating difference in time
        System.out.println("\nDifference in time: " + (timeNoOpt - timeOpt) + " nanoseconds");
    }
}
