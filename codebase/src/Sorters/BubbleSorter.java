package Sorters;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class BubbleSorter {

    public static void sort(LinkedList<Integer> list) {
        int operations = 0;
        int listLength = list.size();

        //Sorting
        for (int i = 0; i < listLength - 1; i++) {
            for (int j = 0; j < listLength - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    //Swapping them numbers
                    int temporary = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temporary);
                }
            }
        }
        System.out.println("Sorted list using a non-optimised BubbleSort: " + list);
    }


    public static void optimisedSort(LinkedList<Integer> list) {

    }




    public static void main(String[] args) {
        LinkedList<Integer> testList = new LinkedList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.add(6);
        testList.add(7);
        testList.add(8);
        testList.add(9);
        testList.add(10);

        Collections.shuffle(testList);
        System.out.println("Before sorting: " + testList);
        sort(testList);


    }




}
