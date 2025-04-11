import DataHandling.City;
import Sorters.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    // Method used for running sorting algorithms. Includes printing of messages and tracks time.
    public static boolean testSort(Sorter sorter, ArrayList<Double> list, int flag){
        ArrayList<Long> times = new ArrayList<>();
        ArrayList<Double> clonedArray = (ArrayList<Double>) list.clone();
        boolean wasSorted;

        for(int i = 0; i < 10; i++){
            //System.out.println("Creating copy of array to sort ...");

            Double[] unsortedArray = clonedArray.toArray(new Double[0]);

            System.out.printf("%d: TESTING SORT ...\n", i + 1);
            long startTime = System.nanoTime();
            sorter.sort(unsortedArray, flag);
            long timeSorting = System.nanoTime() - startTime;

            //System.out.println("Sorting algorithm complete! Verifying...");

            wasSorted = isSorted(unsortedArray);

            if(!wasSorted) {
                System.out.println("Sorting algorithm failed in sorting list! Cancelling test.");
                return false;
            }

            times.add(timeSorting);
        }

        long timeAverage = (times.stream().mapToLong(a -> a).sum()) / 10;

        System.out.printf("\n -> Average sort execution time: %.2f ms (%.4f s)\n", (timeAverage * Math.pow(10, -6)), timeAverage * Math.pow(10, -9));

        // Extra logging based on sorting algorithm
        if(sorter instanceof BubbleSorter){
            System.out.printf("------> Average passes: %10d \n", ((BubbleSorter) sorter).getPasses() / 10);
            System.out.printf("------> Average swaps:  %10d \n", ((BubbleSorter) sorter).getSwaps() / 10);

        } else if(sorter instanceof InsertionSorter){
            System.out.printf("------> Average operations: %10d \n", ((InsertionSorter) sorter).getOperations() / 10);

        } else if(sorter instanceof MergeSorter){
            System.out.printf("------> Average merges: %10d \n", ((MergeSorter) sorter).getMerges() / 10);

        } else if(sorter instanceof QuickSorter){
            System.out.printf("    --> Average comparisons: %10d \n", ((QuickSorter) sorter).getComparisons() / 10);
            System.out.printf("    --> Average partitions:  %10d \n", ((QuickSorter) sorter).getPartitions() / 10);
        }

        // Resets the counters on the sorter
        sorter.reset();

        return true;
    }

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

    // Main method where algorithms are executed, and data is handled
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

            // ________________ FOR RANDOMIZING LIST ORDER ____________________
            //  Collections.shuffle(latitudes);

            // Bubble sort
            System.out.println("\n\nREGULAR BUBBLE SORT");
            System.out.println("_______________________________________________________________________");
            BubbleSorter bubbleSorter = new BubbleSorter();

            // Dry run
            bubbleSorter.sort(latitudes.toArray(new Double[0]).clone(), 0);

            // Running regular bubble sort
            testSort(bubbleSorter, latitudes, 0);

            System.out.println("\n\nOPTIMIZED BUBBLE SORT");
            System.out.println("_______________________________________________________________________");
            testSort(bubbleSorter, latitudes, 1);

            // Insertion sort testing
            System.out.println("\n\nINSERTION SORT");
            System.out.println("_______________________________________________________________________");
            InsertionSorter insertionSorter = new InsertionSorter();
            testSort(insertionSorter, latitudes, 0);

            // Merge sort
            System.out.println("\n\nMERGE SORT");
            System.out.println("_______________________________________________________________________");
            MergeSorter mergeSorter = new MergeSorter();
            testSort(mergeSorter, latitudes, 0);

            // Quick sort
            System.out.println("\n\nQUICK SORT WITH PIVOT LAST (Lomuto)");
            System.out.println("_______________________________________________________________________");
            QuickSorter quickSorter = new QuickSorter();
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
