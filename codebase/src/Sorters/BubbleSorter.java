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
        //System.out.println("Sorted list using a non-optimised BubbleSort: " + list);
        //System.out.println("Number of swaps: " + swaps);
        //System.out.println("Number of passes: " + passes);
    }


    public static void optimisedSort(LinkedList<Integer> list) {
        int listLength = list.size();
        boolean swapped;
        int swaps = 0;
        int passes = 0;
        int lastSwapPosition;
        int end = listLength - 1;

        do {
            swapped = false;
            lastSwapPosition = 0;
            passes++;
            for (int j = 0; j < end; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                    swaps++;
                    lastSwapPosition = j;
                }
            }
            end = lastSwapPosition;
        } while (swapped);

        //System.out.println("Sorted list using an optimised BubbleSort: " + list);
        //System.out.println("Number of swaps: " + swaps);
        //System.out.println("Number of passes: " + passes);
    }


    public static void main(String[] args) {

        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            testList.add(i);
        }

        // Warm-up phase
        for (int i = 0; i < 10; i++) {
            LinkedList<Integer> tempList = new LinkedList<>(testList);
            Collections.shuffle(tempList);
            sort(new LinkedList<>(tempList));
            optimisedSort(new LinkedList<>(tempList));
        }

        long timeOptTotal = 0;
        long timeNoOptTotal = 0;
        int iterations = 100;

        for (int i = 0; i < iterations; i++) {
            LinkedList<Integer> shuffledList = new LinkedList<>(testList);
            Collections.shuffle(shuffledList);

            long startTimeOpt = System.nanoTime();
            optimisedSort(new LinkedList<>(shuffledList));
            long endTimeOpt = System.nanoTime();
            timeOptTotal += (endTimeOpt - startTimeOpt);

            shuffledList = new LinkedList<>(testList);
            Collections.shuffle(shuffledList);

            long startTimeNoOpt = System.nanoTime();
            sort(new LinkedList<>(shuffledList));
            long endTimeNoOpt = System.nanoTime();
            timeNoOptTotal += (endTimeNoOpt - startTimeNoOpt);
        }

        System.out.println("Average time taken with optimised BubbleSort: " + (timeOptTotal / iterations) + " nanoseconds");
        System.out.println("Average time taken with non-optimised BubbleSort: " + (timeNoOptTotal / iterations) + " nanoseconds");



        /* COMMENTED OUT FOR TESTING WITH BENCHMARKKING
        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            testList.add(i);
        }
        Collections.shuffle(testList);

        //Optimised version
        System.out.println("\nBefore sorting with an optimised BubbleSort: " + testList);
        long startTimeOpt = System.nanoTime();
        optimisedSort(new LinkedList<>(testList));
        long endTimeOpt = System.nanoTime();
        long timeOpt = endTimeOpt - startTimeOpt;
        System.out.println("Time taken with an optimised BubbleSort: " + timeOpt + " nanoseconds");


        //Non-optimised version
        System.out.println("\nBefore sorting with non-optimised BubbleSort: " + testList);
        long startTimeNoOpt = System.nanoTime();
        sort(new LinkedList<>(testList));
        long endTimeNoOpt = System.nanoTime();
        long timeNoOpt = endTimeNoOpt - startTimeNoOpt;
        System.out.println("Time taken with a non-optimised BubbleSort: " + timeNoOpt + " nanoseconds");


        //Calculating difference in time
        System.out.println("\nDifference in time: " + (timeNoOpt - timeOpt) + " nanoseconds");
        */
    }
}
