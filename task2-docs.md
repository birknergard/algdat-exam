# Task 2, Insertion Sort:
Example: [5, 1, 2, 3, 6, 4, 3, 2]
Step:
Compare 5 and 1
    if 1 is smaller than five
        Remember index of 1 (1)
        Search for an index that contaisn a value that is smaller than the value of index (1) (we call this i)
        Until i = 0 
        if found index value is smaller than the one you are moving, insert the element before then

## PSEUDOCODE (UNTESTED):
`code`
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
`code`
## Time complexity
    Time complexity should be big O(n^2) exponential time, given that the algorithm contains a nested loop.  
## Space complexity
    Space complexity should be big O(1) constant space, given that we are only storing one element at a time.
## Optimizations
    I believe the algorithm should be faster when sorting a linked list.
    Given that we dont have to shift the other elements forward with every insert.
    Although this is only a marginal optimization.
        
## Sources:
https://en.wikipedia.org/wiki/Insertion_sort




        
        


