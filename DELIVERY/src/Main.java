import DataHandling.City;
import Sorters.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    // Method used for running sorting algorithms. Includes printing of messages and tracks time.
    public static boolean testSort(Sorter sorter, ArrayList<Double> list, int flag){
        ArrayList<Long> times = new ArrayList<>();
        int sorts = 1;
        boolean wasSorted;

        System.out.printf("TESTING SORT %d times ...\n", sorts);
        System.out.printf("DATASET LENGTH: %d elements\n", list.size());
        for(int i = 0; i < sorts; i++){
            Double[] unsortedArray = ((ArrayList<Double>) list.clone()).toArray(new Double[0]);

            long startTime = System.nanoTime();
            sorter.sort(unsortedArray, flag);
            long timeSorting = System.nanoTime() - startTime;

            // Verifies the integrity of the current sort
            wasSorted = isSorted(unsortedArray);

            // If list is not sorted after sort the method exits with error
            if(!wasSorted) {
                System.out.println("Sorting algorithm failed in sorting list! Cancelling test.");
                return false;
            }

            // Adds time taken to array for later parsing
            times.add(timeSorting);
        }

        // Parsing various time statistics
        long timeBest = times.getFirst();
        long timeWorst = times.getFirst();
        long timeAverage = times.getFirst();
        for(int i = 1; i < times.size(); i++){
            if(times.get(i) < timeBest){
               timeBest = times.get(i);
            }
            if(times.get(i) > timeWorst){
                timeWorst = times.get(i);
            }
            timeAverage += times.get(i);
        }
        timeAverage = timeAverage / times.size();
        long difference = timeWorst - timeBest;

        // Test output
        System.out.printf("\n -> Average sort execution time: %.2f ms (%.4f s)", (timeAverage * Math.pow(10, -6)), timeAverage * Math.pow(10, -9));
        System.out.printf("\n -> Best sort: %.2f ms (%.4f s)", (timeBest * Math.pow(10, -6)), timeBest * Math.pow(10, -9));
        System.out.printf("\n -> Worst sort: %.2f ms (%.4f s)", (timeWorst * Math.pow(10, -6)), timeWorst * Math.pow(10, -9));
        System.out.printf("\n -> Difference: %.2f ms (%.4f s)\n", (difference * Math.pow(10, -6)), difference * Math.pow(10, -9));

        // Extra output based on specific sorter class
        if(sorter instanceof BubbleSorter){
            System.out.printf("------> Average passes: %10d \n", ((BubbleSorter) sorter).getPasses() / sorts);
            System.out.printf("------> Average swaps:  %10d \n", ((BubbleSorter) sorter).getSwaps() / sorts);

        } else if(sorter instanceof InsertionSorter){
            System.out.printf("------> Average operations: %10d \n", ((InsertionSorter) sorter).getOperations() / sorts);

        } else if(sorter instanceof MergeSorter){
            System.out.printf("------> Average merges: %10d \n", ((MergeSorter) sorter).getMerges() / sorts);

        } else if(sorter instanceof QuickSorter){
            System.out.printf("    --> Average comparisons: %10d \n", ((QuickSorter) sorter).getComparisons() / sorts);
            System.out.printf("    --> Average partitions:  %10d \n", ((QuickSorter) sorter).getPartitions() / sorts);
        }

        // Resets the counters on the sorter
        sorter.reset();

        return true;
    }

    // Method used to verify whether a list is sorted or not. Way faster than manual checking on the larger dataset.
    private static boolean isSorted(Double[] list){
        // omits first index size we are comparing two adjacent elements at a time,
        // in order to prevent out of bounds exception
        for(int i = 1; i < list.length; i++){
            // Returns false if unordered elements are found
            if(list[i-1] > list[i]) return false;
        }

        // If loop completed the list has to be sorted
        return true;
    }

    // Main method data is parsed and sorting algorithms are executed
    public static void main(String[] args) {
        // Data structure to hold data
        ArrayList<Double> latitudes = new ArrayList<>();

        // Handle data import
        try{
            Scanner reader = new Scanner(new FileInputStream("../worldcities.csv"));

            // Skipping the first line (its just category names)
            reader.nextLine();

            // Parsing data into data structure
            while(reader.hasNext()){
                String raw = reader.nextLine();

                // removes qoutation marks from start and end
                String csvString = raw.substring(1, raw.length() - 1);

                var city = new City(csvString);
                latitudes.add(city.getLat());
            }
               //System.out.println(latitudes);

            /* ______________________________________TESTING INFRASTRUCTURE______________________________________ */
            //  For randomizing list order, comment/uncomment to use
            Collections.shuffle(latitudes);

            // BUBBLE SORT TESTING
            System.out.println("\n\nREGULAR BUBBLE SORT (SHUFFLED)");
            System.out.println("_______________________________________________________________________");
            BubbleSorter bubbleSorter = new BubbleSorter();

            // Dry run to prevent some performance hiccups on the first method invoked by the class
            bubbleSorter.sort(latitudes.toArray(new Double[0]).clone(), 0);

            // Testing begins
            testSort(bubbleSorter, latitudes, 0);

            System.out.println("\n\nOPTIMIZED BUBBLE SORT (SHUFFLED)");
            System.out.println("_______________________________________________________________________");
            testSort(bubbleSorter, latitudes, 1);

            // INSERTION SORT TESTING
            System.out.println("\n\nINSERTION SORT");
            System.out.println("_______________________________________________________________________");
            InsertionSorter insertionSorter = new InsertionSorter();
            testSort(insertionSorter, latitudes, 0);

            // MERGE SORT TESTING
            System.out.println("\n\nMERGE SORT (SHUFFLED)");
            System.out.println("_______________________________________________________________________");
            MergeSorter mergeSorter = new MergeSorter();
            testSort(mergeSorter, latitudes, 0);

            // QUICK SORT TESTING
            QuickSorter quickSorter = new QuickSorter();

            System.out.println("\n\nQUICK SORT WITH PIVOT LAST (Lomuto)");
            System.out.println("_______________________________________________________________________");
            testSort(quickSorter, latitudes, 0);

            System.out.println("\n\nQUICK SORT WITH PIVOT FIRST (Hoare)");
            System.out.println("_______________________________________________________________________");
            testSort(quickSorter, latitudes, 1);

            System.out.println("\n\nQUICK SORT WITH RANDOM FIRST PIVOT (Hoare)");
            System.out.println("_______________________________________________________________________");
            testSort(quickSorter, latitudes, 2);

        } catch (FileNotFoundException e){
            System.out.println("Could not load file.");
        }
    }
}
