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

            if(list.get(unsortedElementIndex) < list.get(insertionComparatorElementIndex)){
                unsortedElement = list.remove(unsortedElementIndex);

                while(insertionComparatorElementIndex >= 0){
                    operations++;
                    if(insertionComparatorElementIndex == 0 && list.get(insertionComparatorElementIndex) > unsortedElement){
                        list.addFirst(unsortedElement);
                        break;
                    }

                    if(list.get(insertionComparatorElementIndex) <= unsortedElement ){
                       list.add(insertionComparatorElementIndex + 1, unsortedElement);
                       break;
                    }

                    insertionComparatorElementIndex--;
                }
            }
        }

        return operations;
    }

    // For internal testing
    public static void main(String[] args) {
        LinkedList<Double> testList = new LinkedList<>();
        // Generate a large randomized list for testing
        for(int i = 0; i < 10_000; i++){
            testList.push(Math.floor(Math.random() * 100000) / 100);
        }

        Collections.shuffle(testList);
        System.out.print("Before sort: ");
        System.out.println(testList);

        int iterations = sort(testList);
        System.out.print("After sort: ");
        System.out.println(testList);
        System.out.printf("Iteration count: %d\n", iterations);
    }
}
