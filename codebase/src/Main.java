import DataHandling.City;
import Sorters.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    // Method used for running sorting algorithms. Includes printing of messages and tracks time.
    public static boolean testSort(Sorter sorter, Double[] list, int flag){
        boolean wasSorted;
        //System.out.println("Creating copy of array to sort ...");
        Double[] unsortedArray = list.clone();



        System.out.printf("Starting sort execution on list of size %d...\n\n", list.length);
        long startTime = System.nanoTime();
        sorter.sort(unsortedArray, flag);
        long timeSorting = System.nanoTime() - startTime;

        //System.out.println("Sorting algorithm complete! Verifying...");

        wasSorted = isSorted(unsortedArray);

        if(wasSorted) System.out.println("Sorting algorithm successfully sorted list!");
        if(!wasSorted) System.out.println("Sorting algorithm failed in sorting list!");

        System.out.printf("\n -> Sort execution time: %.2f ms (%.4f s)\n", (timeSorting * Math.pow(10, -6)), timeSorting * Math.pow(10, -9));

        // Extra logging based on sorting algorithm
        if(sorter instanceof BubbleSorter){
            System.out.printf("------> Passes: %10d \n", ((BubbleSorter) sorter).getPasses());
            System.out.printf("------> Swaps:  %10d \n", ((BubbleSorter) sorter).getSwaps());

        } else if(sorter instanceof InsertionSorter){
            System.out.printf("------> Operations: %10d \n", ((InsertionSorter) sorter).getOperations());

        } else if(sorter instanceof MergeSorter){
            System.out.printf("------> Merges: %10d \n", ((MergeSorter) sorter).getMerges());

        } else if(sorter instanceof QuickSorter){
            System.out.printf("    --> Comparisons: %10d \n", ((QuickSorter) sorter).getComparisons());
            System.out.printf("    --> Partitions:  %10d \n", ((QuickSorter) sorter).getPartitions());
        }

        //System.out.printf("Time elapsed since execution start: %.2f ms\n", (timeElapsed * Math.pow(10,-6)));
        return wasSorted;
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
        LinkedList<Double> latitudes = new LinkedList<>();

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

            // Randomize order of list so its unsorted
            //Collections.shuffle(latitudes);
            // Convert datastructure (LinkedList) to static array (Double[]) for use in sorting methods
            Double[] latitudesArray = latitudes.toArray(new Double[0]);

            // Bubble sort
            System.out.println("\n\nREGULAR BUBBLE SORT");
            System.out.println("_______________________________________________________________________");
            BubbleSorter bubbleSorter = new BubbleSorter();
            // Dry run
            bubbleSorter.sort(latitudesArray.clone(), 0);

            // Running regular bubble sort
            testSort(bubbleSorter, latitudesArray, 0);

            System.out.println("\n\nOPTIMIZED BUBBLE SORT");
            System.out.println("_______________________________________________________________________");
            testSort(bubbleSorter, latitudesArray, 1);

            // Insertion sort testing
            System.out.println("\n\nINSERTION SORT");
            System.out.println("_______________________________________________________________________");
            InsertionSorter insertionSorter = new InsertionSorter();
            testSort(insertionSorter, latitudesArray, 0);

            // Merge sort
            System.out.println("\n\nMERGE SORT");
            System.out.println("_______________________________________________________________________");
            MergeSorter mergeSorter = new MergeSorter();
            testSort(mergeSorter, latitudesArray, 0);

            // Quick sort
            System.out.println("\n\nQUICK SORT WITH PIVOT LAST (Lomuto)");
            System.out.println("_______________________________________________________________________");
            QuickSorter quickSorter = new QuickSorter();
            testSort(quickSorter, latitudesArray, 0);

            System.out.println("\n\nQUICK SORT WITH PIVOT FIRST (Hoare)");
            System.out.println("_______________________________________________________________________");
            testSort(quickSorter, latitudesArray, 1);

            System.out.println("\n\nQUICK SORT WITH RANDOM FIRST PIVOT (Hoare)");
            System.out.println("_______________________________________________________________________");
            testSort(quickSorter, latitudesArray, 2);

        } catch (FileNotFoundException e){
            System.out.println("Could not load file.");
        }
    }
}
