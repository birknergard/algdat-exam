# Task 2, Insertion Sort:

## Description
Insertion sort is an algorithm (!!!)

## PSEUDOCODE 
```
    List = unsorted list of elements

    // We start with the second element in the list, since we are comparing backwards.
    UnsortedElementIndex = 1

    WHILE UnsortedElementIndex < List.Length:
        IF List[UnsortedElementIndex] < List[UnsortedElementIndex - 1]: 
            // Store the unsorted element 
            UnsortedElement = List[UnsortedElementIndex]

            // Set comparison element to start position
            InsertionComparatorElementIndex = UnsortedElementIndex - 1

            WHILE InsertionComparatorElementIndex >= 0:
                
                // shift copy the new element forwards one index (to make space for unsorted element, or next shift) 
                List[InsertionComparatorElementIndex + 1] = List[InsertionComparatorElementIndex]

                IF List[InsertionComparatorElementIndex] <= UnsortedElement:
                    BREAK
                ELSE:
                    InsertionComparatorElementIndex--;
                ENDIF
                
                List[InsertionComparatorElementIndex + 1] = UnsortedElement;
            ENDWHILE

        ElSE:
            UnsortedElementIndex++;
        ENDIF
    ENDWHILE
```

## Time and space complexity
Time complexity should be big O(n^2) exponential time, given that the algorithm contains a nested loop.  
Space complexity should be big O(1) constant space, given that we are only storing one element at a time.

## Optimizations
I believe the algorithm should be faster when sorting a linked list. Given that we dont have to shift the other elements forward with every insert. Although this is only a marginal optimization.
        
## Sources:
https://en.wikipedia.org/wiki/Insertion_sort




        
        


