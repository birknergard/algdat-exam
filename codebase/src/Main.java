import DataHandling.City;
import Sorters.QuickSorter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    // Method used for running sorting algorithms. Includes printing of messages and tracks time.
    public boolean testSort(Sorter sorter, LinkedList<Double> list, int maxOperations){
        int operations = 0;
        long startTime = System.nanoTime();

        sorter.sort(list);

        long timeElapsed = System.nanoTime() - startTime;
        System.out.printf("Time elapsed since execution start: %.2f ms", (timeElapsed * Math.pow(10,-6)));
        return true;
    }

    private static boolean isSorted(LinkedList<Double> list){
        // omits first index size we are comparing two adjacent elements at a time,
        // in order to prevent out of bounds exception
        for(int i = 1; i < list.size(); i++){

            // Returns false if unordered elements are found
            if(list.get(i-1) > list.get(i)) return false;
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
            System.out.println(reader.nextLine());

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
            Collections.shuffle(latitudes);
            // Use data structure (LinkedList) in sorting methods

            // TODO: Bubble sort

            // TODO: Insertion sort

            // TODO: Merge sort

            // TODO: Quick sort

            // Print output from sorting method

        } catch (FileNotFoundException e){
            System.out.println("Could not load file.");
        }
    }
}

