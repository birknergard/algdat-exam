package Sorters;

public class InsertionSorter implements Sorter{

    private int logOperations;

    public InsertionSorter(){
        logOperations = 0;
    }

    public int getOperations() {
        return logOperations;
    }

    public void reset(){
        this.logOperations = 0;
    }

    public void sort(Double[] list, int flag){
        int listLength = list.length;
        double unsortedElement;

        for(int unsortedElementIndex = 1; unsortedElementIndex < listLength; unsortedElementIndex++){
            logOperations++;
            int insertionComparatorElementIndex = unsortedElementIndex - 1;

            if(list[unsortedElementIndex] < list[insertionComparatorElementIndex]){
                // Store the unsorted element
                unsortedElement = list[unsortedElementIndex];

                while(insertionComparatorElementIndex >= 0){
                    logOperations++;
                    // shift copy
                    list[insertionComparatorElementIndex + 1] = list[insertionComparatorElementIndex];

                    // Exit loop if placement is found...
                    if(list[insertionComparatorElementIndex] <= unsortedElement) {
                        break;
                    }

                    // ...continue if otherwise
                    insertionComparatorElementIndex--;
                }

                // Insert the unsorted element at its sorted position in the array
                list[insertionComparatorElementIndex + 1] = unsortedElement;
            }
        }
        // Logging constant
        logOperations += 8;
    }
}
