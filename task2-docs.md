# Task 2, Insertion Sort:

## Description
Insertion sort is an algorithm (!!!)

## PSEUDOCODE 
```
    List = unsorted list of elements
    ListLength = Length of list

    // We start with the second element in the list, since we are comparing backwards.
    UnsortedElementIndex = 1

    WHILE UnsortedElementIndex < ListLength:
        IF List[UnsortedElementIndex] < List[UnsortedElementIndex - 1]: 
            InsertionComparatorElementIndex = UnsortedElementIndex - 1
            Remove element from List[UnsortedElementIndex]

            WHILE InsertionComparatorElementIndex != 0:
                IF List[InsertionComparatorElementIndex] <= List[UnsortedElementIndex]:
                    List.insert(atIndex = InsertionComparatorElementIndex, value=List[UnsortedElementIndex])
                    ENDIF

                ELSE:
                    InsertionComparatorElementIndex--;

                ENDIF
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




        
        


