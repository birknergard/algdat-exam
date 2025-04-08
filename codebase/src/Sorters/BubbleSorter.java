package Sorters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSorter implements Sorter{
    //Data fields
    int swaps = 0;
    int passes = 0;
    int operations = 0;

    public BubbleSorter(){
        swaps = 0;
        passes = 0;
        operations = 0;
    }

    //Swap function, copied from QuickSorter
    private void swap(Double[] list, int indexA, int indexB){
        double temp = list[indexA];
        list[indexA] = list[indexB];
        list[indexB] = temp;
    }

    // Sorting method. flag = 1 for unoptimized, and 0 for optimized
    public void sort(Double[] list, int flag) {
        int arrayLength = list.length;

        switch(flag){
            case 0:
                boolean swapped;
                int lastSwapPosition;
                int end = arrayLength - 1;

                do {
                    swapped = false;
                    lastSwapPosition = 0;
                    this.passes++;
                    for (int j = 0; j < end; j++) {
                        if (list[j] > list[j + 1]) {
                            swap(list, j, j + 1);
                            swapped = true;
                            this.swaps++;
                            lastSwapPosition = j;
                        }
                    }
                    end = lastSwapPosition;
                } while (swapped);

                //System.out.println("Sorted list using an optimised Bubble Sort: ");
                for (double num : list) {
                    System.out.print(num + ", ");
                }
                //System.out.println("\nNumber of swaps: " + swaps);
                //System.out.println("Number of passes: " + passes + "\n");
                break;

            case 1:
                //Sorting
                for (int i = 0; i < arrayLength - 1; i++) {
                    this.passes++;
                    for (int j = 0; j < arrayLength - i - 1; j++) {
                        if (list[j] > list[j + 1]) {
                            //Swapping them numbers
                            swap(list, j, j + 1);
                            this.swaps++;
                        }
                    }
                }
                System.out.println("Sorted list using a non-optimised Bubble Sort: ");
                for (double num : list) {
                    System.out.print(num + ", ");
                }
                System.out.println("\nNumber of swaps: " + swaps);
                System.out.println("Number of passes: " + passes + "\n");
                break;
            default:
                System.out.println("Invalid flag. Needs to be either 0 (optimized) or 1 (unoptimized).");
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
