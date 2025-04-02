# Task 4 - QuickSort

## Description
Quicksort is a sorting algorithm which used a divide and conquer methodology to sort an unsorted list.
The algorithm sorts by utilizing a "pivot", for which elements are put on the left (smaller) or right (larger) depending on the size of said pivot. The list then split in two on the pivot and the same method is repeated until the list is sorted.

## Pseudocode 
---
´// comments are written for context within the pseudocode´ 
Below is the pseudocode for the quicksort algorithm. 
```
FUNC QuickSort(LowerBoundaryIndex, HigherBoundaryIndex, List):

    // Find the pivot, in this case the higher boundary
    PivotValue = List[HigherBoundaryIndex]
    
    // Create two index "pointers" to control swapping operations
    IteratorI, IteratorJ = LowerBoundary

    // Partitioning (or sorting) algorithm
    FOR LowerBoundary TO HigherBoundary:
        IF List[Iterator] <= PivotValue:
            Swap(List[IteratorJ], List[IteratorI])            
            IteratorJ++
        ENDIF
        IteratorI++
    ENDFOR

    PivotIndex = IteratorI

    // Recursively sort the subarrays created
    QuickSort(LowerBoundary, PivotIndex - 1)
    QuickSort(PivotIndex + 1, HigherBoundary)
ENDFUNC
```

---
## Time and space complexity

## Optimizations  
The simplest optimization for QuickSort is to randomize the Pivot index.
