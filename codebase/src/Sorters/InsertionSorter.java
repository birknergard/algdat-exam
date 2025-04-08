package Sorters;

public class InsertionSorter implements Sorter{
    public int sort(Double[] list){
        int operations = 0;
        int listLength = list.length;
        double unsortedElement;

        for(int unsortedElementIndex = 1; unsortedElementIndex < listLength; unsortedElementIndex++){
            int insertionComparatorElementIndex = unsortedElementIndex - 1;

            if(list[unsortedElementIndex] < list[insertionComparatorElementIndex]){
                // Store the unsorted element
                unsortedElement = list[unsortedElementIndex];

                while(insertionComparatorElementIndex >= 0){
                    // shift copy
                    list[insertionComparatorElementIndex + 1] = list[insertionComparatorElementIndex];

                    // Exit loop if placement is found...
                    if(list[insertionComparatorElementIndex] <= unsortedElement) {
                        break;
                    }

                    // ...continue if otherwise
                    insertionComparatorElementIndex--;
                }

                list[insertionComparatorElementIndex + 1] = unsortedElement;
            }
        }

        return operations;
    }

    /* For internal testing
    public static void main(String[] args) {
        ArrayList<Double> testList = new ArrayList<>();
        // Generate a large randomized list for testing
        for(int i = 0; i < 5; i++){
            testList.add((double) i);
            //testList.add(Math.floor(Math.random() * 100000) / 100);
        }

        Collections.shuffle(testList);

        //Double[] array = testList.toArray(new Double[0]);
        Double[] array = {1.0, 2.0, 3.0 ,5.2 ,6.4, 7.0, 0.0, 1.2, 0.5};

        System.out.print("Before sort: ");
        System.out.println(Arrays.toString(array));

        int iterations = sort(array);
        System.out.print("After sort: ");
        System.out.println(Arrays.toString(array));
        System.out.printf("Iteration count: %d\n", iterations);
    }
     */
}
