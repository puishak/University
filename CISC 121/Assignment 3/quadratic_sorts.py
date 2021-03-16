"""This module uses the class slides as a guideline to implement a modified 
version of 3 sorting algorithms (insertion, selection, bubble). For each of the 
algorithms it outputs how many times the outer loop, inner loop and, how many 
swaps was executed.
"""
from typing import List

def insertion(a_list: List[float]) -> List[int]:
    """Sort the list a_list in ascending order implementing the insertion sort 
    algorithm and output a list of 3 integers - 
    the 1st integer should represent the number of outer loop execution
    the 2nd integer should represent the number of inner loop execution
    the 3rd integer should represent the number of swaps executed
    """
    outer_loop = 0
    inner_loop = 0
    swaps = 0
    
    for key in range(1, len(a_list)):
        outer_loop += 1
        i = key
        while i > 0 and a_list[i - 1] > a_list[i]:
            inner_loop += 1
            if a_list[i-1] != a_list[i]:
                swaps += 1
                a_list[i-1], a_list[i] = a_list[i], a_list[i-1]
            i = i - 1
            
    return [outer_loop, inner_loop, swaps]



def selection(a_list: List[float]) -> List[int]:
    """Sort the list a_list in ascending order implementing the selection sort 
    algorithm and output a list of 3 integers - 
    the 1st integer should represent the number of outer loop execution
    the 2nd integer should represent the number of inner loop execution
    the 3rd integer should represent the number of swaps executed
    """
    outer_loop = 0
    inner_loop = 0
    swaps = 0    
    
    n = len(a_list)
    for i in range(n-1):
        outer_loop += 1
        current_min = i
        
        for j in range(i + 1, n):
            inner_loop += 1
            
            if a_list[j] < a_list[current_min]:
                current_min = j
        
        if current_min != i:
            swaps += 1
            a_list[i], a_list[current_min] = a_list[current_min], a_list[i]
    
    return [outer_loop, inner_loop, swaps]



def bubble(a_list: List[float]) -> List[int]:
    """Sort the list a_list in ascending order implementing the bubble sort 
    algorithm and output a list of 3 integers - 
    the 1st integer should represent the number of outer loop execution
    the 2nd integer should represent the number of inner loop execution
    the 3rd integer should represent the number of swaps executed
    """
    outer_loop = 0
    inner_loop = 0
    swaps = 0
    
    swap_flag = True
    n = len(a_list)
    while swap_flag:
        outer_loop += 1
        swap_flag = False
        for i in range(1, n):
            inner_loop += 1
            if a_list[i-1] > a_list[i]:
                swaps += 1
                a_list[i - 1], a_list[i] = a_list[i], a_list[i - 1]
                swap_flag = True
        n -= 1 
    
    return [outer_loop, inner_loop, swaps]
