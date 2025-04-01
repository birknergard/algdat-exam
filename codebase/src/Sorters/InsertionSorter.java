package Sorters;
import java.util.Collections;
import java.util.LinkedList;

public class InsertionSorter {
    public static int sort(LinkedList<Double> list){
        int operations = 0;
        int listLength = list.size();
        double unsortedElement;

        for(int unsortedElementIndex = 1; unsortedElementIndex < listLength; unsortedElementIndex++){
            operations++;
            int insertionComparatorElementIndex = unsortedElementIndex - 1;
            operations++;

            if(list.get(unsortedElementIndex) < list.get(insertionComparatorElementIndex)){
                operations++;
                unsortedElement = list.remove(unsortedElementIndex);
                operations++;

                while(insertionComparatorElementIndex >= 0){
                    operations++;
                    if(insertionComparatorElementIndex == 0 && list.get(insertionComparatorElementIndex) > unsortedElement){
                        operations++;
                        list.addFirst(unsortedElement);
                        operations++;
                        break;
                    }

                    if(list.get(insertionComparatorElementIndex) <= unsortedElement ){
                        operations++;
                       list.add(insertionComparatorElementIndex + 1, unsortedElement);
                        operations++;
                       break;
                    }

                    insertionComparatorElementIndex--;
                    operations++;
                }
            }
        }

        return operations;
    }

    // For internal testing
    private static void printList(LinkedList<Double> list){
        System.out.print("[");
        for(int i = 0; i < list.size(); i++){
            System.out.printf(" %.3f,", list.get(i));
        }
        System.out.print("]\n");
    }

    public static void main(String[] args) {
        LinkedList<Double> testList = new LinkedList<>();
        testList.add(10.0);
        testList.add(0.0);
        testList.add(20.0);
        testList.add(73.0);
        testList.add(1.0);
        testList.add(50.0);
        testList.add(5.0);
        testList.add(100.0);
        testList.add(4572.0);
        testList.add(1.0);
        testList.add(2.0);

        Collections.shuffle(testList);
        System.out.print("Before sort: ");
        printList(testList);

        int iterations = sort(testList);
        System.out.print("After sort: ");
        printList(testList);
        System.out.printf("Iteration count: %d\n", iterations);
    }
}
