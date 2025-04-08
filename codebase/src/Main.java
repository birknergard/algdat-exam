import DataHandling.City;
import Sorters.BubbleSorter;
import Sorters.InsertionSorter;
import Sorters.QuickSorter;
import Sorters.Sorter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    // Method used for running sorting algorithms. Includes printing of messages and tracks time.
    public static boolean testSort(Sorter sorter, Double[] list, int flag){
        boolean wasSorted;
        System.out.println("Creating copy of array to sort ...");
        Double[] unsortedArray = list.clone();


        /*if(sorter instanceof BubbleSorter){
            System.out.println("Performing dry run ...");
            sorter.sort(list.clone(),1);
        }*/

        System.out.printf("Starting sort execution on list of size %d...\n\n", list.length);
        long startTime = System.nanoTime();
        sorter.sort(unsortedArray, flag);
        long timeSorting = System.nanoTime() - startTime;

        System.out.println("Sorting algorithm complete! Verifying...");

        wasSorted = isSorted(unsortedArray);

        if(wasSorted) System.out.println("Sorting algorithm successfully sorted list!");
        if(!wasSorted) System.out.println("Sorting algorithm failed in sorting list!");

        long timeElapsed = System.nanoTime() - startTime;
        System.out.printf("\n --> Time for sorting: %.2f ms\n", (timeSorting * Math.pow(10,-6)));
        System.out.printf("Time elapsed since execution start: %.2f ms\n", (timeElapsed * Math.pow(10,-6)));
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
            System.out.println("\n\nBUBBLE SORT");
            System.out.println("_______________________________________________________________________");
            BubbleSorter bubbleSorter = new BubbleSorter();
            // Dry run
            bubbleSorter.sort(latitudesArray.clone(), 0);
            testSort(bubbleSorter, latitudesArray, 0);
            testSort(bubbleSorter, latitudesArray, 1);

            /* Insertion sort testing
            System.out.println("\n\nINSERTION SORT");
            System.out.println("_______________________________________________________________________");
            InsertionSorter insertionSorter = new InsertionSorter();
            testSort(insertionSorter, latitudesArray, 0);
             */

            /* TODO: Merge sort
            System.out.println("MERGE SORT");
            System.out.println("_______________________________________________________________________");
             */


            /* Quick sort
            System.out.println("\n\nQUICK SORT");
            System.out.println("_______________________________________________________________________");
            QuickSorter quickSorter = new QuickSorter();
            testSort(quickSorter, latitudesArray, 0);
             */

            // Print output from sorting method

        } catch (FileNotFoundException e){
            System.out.println("Could not load file.");
        }
    }
}
