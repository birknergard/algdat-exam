package Sorters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * CREATED BY CANDIDATE 119
 * */
public class BubbleSorter implements Sorter{
    //Data fields
    private int logSwaps = 0;
    private int logPasses = 0;

    public BubbleSorter(){
        this.logSwaps = 0;
        this.logPasses = 0;
    }

    public int getPasses() {
        return this.logPasses;
    }

    public int getSwaps() {
        return this.logSwaps;
    }

    public void reset(){
        this.logPasses = 0;
        this.logSwaps = 0;
    }

    //Swap function, copied from QuickSorter
    private void swap(Double[] list, int indexA, int indexB){
        double temp = list[indexA];
        list[indexA] = list[indexB];
        list[indexB] = temp;
    }

    // Sorting method. flag = 0 for unoptimized, and 1 for optimized
    public void sort(Double[] list, int flag) {
        int arrayLength = list.length;
        switch(flag){
            // Unoptimized Bubble sort
            case 0:
                // Outer loop runs through the array, each iteration is one pass
                for (int i = 0; i < arrayLength - 1; i++) {
                    // For logging
                    this.logPasses++;

                    // Inner loop compares and swaps elements
                    for (int j = 0; j < arrayLength - 1; j++) {
                        //If current element is greater than the next, swap them
                        if (list[j] > list[j + 1]) {
                            this.logSwaps++;
                            swap(list, j, j + 1);
                        }
                    }
                }
                break;

            // Optimized Bubble sort
            case 1:
                boolean swapped;
                int lastSwapPosition;
                int end = arrayLength - 1;

                do {
                    this.logPasses++;

                    // Toggles if swaps are made during pass, defaulted to false
                    swapped = false;

                    lastSwapPosition = 0; // Initialize variable

                    // Only bubble through the unsorted part of the array
                    for (int j = 0; j < end; j++) {

                        // If current element is greater than the last, swap them
                        if (list[j] > list[j + 1]) {
                            this.logSwaps++;
                            swap(list, j, j + 1);
                            swapped = true; // Declare that the swap occured
                            lastSwapPosition = j; // Save last swapped position
                        }
                    }

                    /* Limit next pass to the last position where a swap occurred,
                    because everything after that is already swapped
                    */
                    end = lastSwapPosition;
                } while (swapped); // Repeat until no swaps are made (list is sorted)
                break;

            // Handling misinput
            default:
                System.out.println("Invalid flag. Needs to be either 0 (optimized) or 1 (unoptimized).");
                break;
        }
    }


    // birk: Method for internal testing
    public void internalTest() {
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

        //Optimised version
        Double[] optimisedList = Arrays.copyOf(testList, testList.length);
        long startOpt = System.nanoTime();
        sort(optimisedList, 1);
        long endOpt = System.nanoTime();
        long timeOpt = endOpt - startOpt;
        System.out.println("\nTime taken with an optimised Bubble Sort: ");

        //Non-optimised version
        Double[] nonOptimisedList = Arrays.copyOf(testList, testList.length);
        long startNonOptimised = System.nanoTime();
        sort(nonOptimisedList, 0);
        long endNonOptimised = System.nanoTime();
        long timeNoOpt = endNonOptimised - startNonOptimised;
        System.out.println("\nTime taken with non-optimised Bubble Sort: ");
        //Calculating difference in time
        System.out.println("\nDifference in time: " + (timeNoOpt - timeOpt) + " nanoseconds");
        /* FOR BENCHMARKING - Warm-Up Phase
        for (int i = 0; i < 10; i++) {
            ArrayList<Double> tempList = new ArrayList<>(testList);
            Collections.shuffle(tempList);
            sort(new ArrayList<>(tempList));
            optimisedSort(new ArrayList<>(tempList));
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
        System.out.println("Average time taken with non-optimised BubbleSort: " + (timeNoOptTotal / iterations) + " nanoseconds"); */

    }
}
