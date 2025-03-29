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

        // Handle data import
        try{
            LinkedList<City> cities = new LinkedList<>();
            Scanner reader = new Scanner(new FileInputStream("../worldcities.csv"));
            System.out.println(reader.next());

            // Parse data into data structure

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
