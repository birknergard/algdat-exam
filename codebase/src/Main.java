import DataHandling.City;
import Sorters.QuickSorter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {

    // Prints the list (of latitudes/floats)
    public static void printList(LinkedList<Float> list){
        System.out.print("[");
        for(int i = 0; i < list.size(); i++){
            System.out.printf(" %f,", list.get(i));
        }
        System.out.print("]\n");
    }

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
                String csvString = raw.substring(1, raw.length() - 1);

                var city = new City(csvString);
                latitudes.add(city.getLat());
            }
               //FOR VERIFYING: printList(latitudes);

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
            return;
        }
    }
}

