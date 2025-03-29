import DataHandling.City;
import Sorters.QuickSorter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    // Main method where algorithms are executed, and data is handled
    public static void main(String[] args) {

        // Data structure to hold data
        LinkedList<Float> latitudes = new LinkedList<>();

        // Handle data import
        try{
            Scanner reader = new Scanner(new FileInputStream("../worldcities.csv"));

            // Skipping the first line (its just category names)
            System.out.println(reader.nextLine());

            // Parsing data into data structure
            while(reader.hasNext()){
                String raw = reader.nextLine();
                // removes qoutation marks from start and end
                System.out.println("String length: " + raw.length());
                String csvString = raw.substring(1, raw.length() - 1);

                var city = new City(csvString);
                latitudes.add(city.getLat());
            }

            // Randomize order of list so its unsorted

            // Use data structure (LinkedList) in sorting methods

        } catch (FileNotFoundException e){
            System.out.println("Could not load file.");
            return;
        }

        // Bubble sort

        // Insertion sort

        // Merge sort

        // Quick sort


        // Print output from sorting method


    }
}
