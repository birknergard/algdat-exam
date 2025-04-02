# Task 4 - QuickSort

## Description
Quicksort is a sorting algorithm which used a divide and conquer methodology to sort an unsorted list.
The algorithm sorts by utilizing a "pivot", for which elements are put on the left (smaller) or right (larger) depending on the size of said pivot. The list then split in two on the pivot and the same method is repeated until the list is sorted.

## Pseudocode 
`// comments are written for context within the pseudocode`

Below is the pseudocode for the quicksort algorithm. It sets pivot as high (Lomuto) 

```
FUNC QuickSort(LowerBoundaryIndex, HigherBoundaryIndex, List):

    // pivot is always set as the higher boundary (Lomuto)
    PivotValue = List[HigherBoundaryIndex]
    
    // Create two index "pointers" to control swapping operations, start at same position
    IteratorI, IteratorJ = LowerBoundary

    // Partitioning (or sorting) algorithm, pivot is ignored for initial partition
    FOR LowerBoundary TO HigherBoundary - 1: 
        IF List[Iterator] <= PivotValue:
            SWAP(List[IteratorJ], List[IteratorI])            
            IteratorJ++
        ENDIF
        IteratorI++
    ENDFOR

    // Since pivot was ignored, it can now be swapped to the correct position in the list
    SWAP(IteratorI, HigherBoundaryIndex)
    PivotIndex = IteratorI

    // Recursively sort the two partitions 
    QuickSort(LowerBoundary, PivotIndex - 1)
    QuickSort(PivotIndex + 1, HigherBoundary)

ENDFUNC
```

Below is the pseudocode for quicksort algorithm with hoare partitioning scheme. It sets pivot as low 

```
FUNC QuickSort(LowerBoundaryIndex, HigherBoundaryIndex, List):

    Pivot = LowerBoundaryIndex
    Left = LowerBoundaryIndex - 1
    High = HigherBoundaryIndex + 1
    
    FOR(EVER):
        // Increments left until it finds a number larger than the pivot
        WHILE Left <= Right:
            IF List[Left] < List[Pivot]:
                Left++;
            ENDIF
        ENDWHILE

        // Decrements right until it finds a number smaller than the pivot
        WHILE Right >= Left: 
            IF List[Right] > List[Pivot]:
                Right--;
            ENDIF
        ENDWHILE

        // Exits if left and right crossed
        IF Left >= Right:
            RETURN LEFT;
        ENDIF

        SWAP(List[Left], List[Right])
    ENDFOR

    

    // Recursively sort the subarrays created
    QuickSort(LowerBoundary, PivotIndex - 1)
    QuickSort(PivotIndex + 1, HigherBoundary)
ENDFUNC
```
---
## Time and space complexity

## Optimizations  
The simplest optimization for QuickSort is to randomize the Pivot index.
