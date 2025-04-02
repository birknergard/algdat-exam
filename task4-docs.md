# Task 4 - QuickSort
## Pseudocode 
`code`
FUNC QuickSort(LowerBoundaryIndex, HigherBoundaryIndex, List):
    //Find the pivot
    PivotValue = List[HigherBoundaryIndex]

    IteratorI = LowerBoundary
    IteratorJ = LowerBoundary

    FOR LowerBoundary TO HigherBoundary:
        IF List[Iterator] <= PivotValue:
            Swap(List[IteratorJ], List[IteratorI])            
            IteratorJ++
        ENDIF
        IteratorI++
    ENDFOR
ENDFUNC
`code`

## Time and space complexity

## Optimizations  
