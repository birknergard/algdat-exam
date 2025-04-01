package Sorters;
import java.util.Collections;
import java.util.LinkedList;

public class InsertionSorter {
    public static void sort(LinkedList<Integer> list){
        int listLength = list.size();
        int unsortedElement;
        int unsortedElementIndex = 1;

        while(unsortedElementIndex < listLength){
            int insertionComparatorElementIndex = unsortedElementIndex - 1;
            if(list.get(unsortedElementIndex) < list.get(insertionComparatorElementIndex)){
                unsortedElement = list.remove(unsortedElementIndex);
                while(insertionComparatorElementIndex >= 0){
                    if(list.get(insertionComparatorElementIndex) < unsortedElement){
                       list.add(insertionComparatorElementIndex + 1, unsortedElement);
                    }

                    insertionComparatorElementIndex--;
                }
            }

            unsortedElementIndex++;
        }
    }

    // For internal testing
    private static void printList(LinkedList<Integer> list){
        System.out.print("[");
        for(int i = 0; i < list.size(); i++){
            System.out.printf(" %d,", list.get(i));
        }
        System.out.print("]\n");
    }

    public static void main(String[] args) {
        LinkedList<Integer> testList = new LinkedList<>();
        testList.add(10);
        testList.add(0);
        testList.add(20);
        testList.add(73);
        testList.add(1);
        testList.add(50);
        testList.add(5);
        testList.add(100);
        testList.add(4572);
        testList.add(1);
        testList.add(2);

        Collections.shuffle(testList);
        sort(testList);
        printList(testList);
    }
}
