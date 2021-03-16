"""This module has 3 functions to be called by A2.py

Author: Shakib Absar
Section: 001
Student Number: 20245608
"""
from typing import List

def cal_factorial(x: int) -> int:
    """Returns the factorial of 'x'. 'x' has to be a positive integer."""
    #Initializing 'factorial' to 1 because it will be multiplied by x later.
    factorial = 1 
        
    while(x != 0):
        factorial *= x 
        x -= 1
    
    return factorial


def list_multiples(number, length) -> List[int]:
    """Return a list of multiples of 'number' up to 'length'
    'number' has to be non-negative integer.
    'length' has to be positive integer.
    """
    
    output = []
    
    for i in range(1, length + 1):
        # the minimum value on the range function is set to 1 so that the
        # first value on the output list is not 0
        output.append(number*i)
    
    return output


def find_max(a_list: List[int]) -> int:
    """Return the largest number from the list of integers 'a_lsit'.
    'a_list' cannot be empty
    """
    
    largest = a_list[0]
    # The variable 'largest' needed to be initiated to one of the values from 
    # 'a_list' so that the comparison is done between the values from the 
    # list. Since a_list cannot be empty this line should not give any error.
    
    for i in a_list:
        if largest < i:
            largest = i
    
    return largest


if __name__ == "__main__":
    # Module testing
    
    # function cal_factorial
    
    # Case 1
    x = 1
    print("The input is", x)
    print(x, "factorial is", cal_factorial(x))
    print("\n")
    
    # Case 2
    x = 2
    print("The input is", x)
    print(x, "factorial is", cal_factorial(x))
    print("\n")
    
    # Case 3
    x = 3
    print("The input is", x)
    print(x, "factorial is", cal_factorial(x))
    print("\n")
    
    # Case 4
    x = 4
    print("The input is", x)
    print(x, "factorial is", cal_factorial(x))
    print("\n")
    
    # Case 5
    x = 5
    print("The input is", x)
    print(x, "factorial is", cal_factorial(x))
    print("\n")
    
    # function list_multiples
    
    # Case 1
    number = 2
    length = 5
    print("The input values for 'number' and 'length' are", number, length)
    output_list = list_multiples(number, length)
    print(length, "multiples of", number, ": ", output_list)
    print("\n")
    
    # Case 2
    number = 4
    length = 1
    print("The input values for 'number' and 'length' are", number, length)
    output_list = list_multiples(number, length)    
    print(length, "multiples of", number, ": ", output_list)
    print("\n")
    
    # Case 3
    number = 0
    length = 3
    print("The input values for 'number' and 'length' are", number, length)
    output_list = list_multiples(number, length)    
    print(length, "multiples of", number, ": ", output_list)
    print("\n")
    
    # Case 4
    number = 0
    length = 1
    print("The input values for 'number' and 'length' are", number, length)
    output_list = list_multiples(number, length)    
    print(length, "multiples of", number, ": ", output_list)
    print("\n")
    
    # Case 5
    number = 9
    length = 9
    print("The input values for 'number' and 'length' are", number, length)
    output_list = list_multiples(number, length)    
    print(length, "multiples of", number, ": ", output_list)
    print("\n")
    
    # function find_max
    
    # Case 1
    input_list = [3, 3, 3]
    print("The input list is", input_list)
    print("the maximum value from the list is", find_max(input_list))
    print("\n")
    
    # Case 2
    input_list = [-23, -45, -9]
    print("The input list is", input_list)
    print("the maximum value from the list is", find_max(input_list))    
    print("\n")
    
    # Case 3
    input_list = [0]
    print("The input list is", input_list)
    print("the maximum value from the list is", find_max(input_list))    
    print("\n")
    
    # Case 4
    input_list = [-3, 0, 3]
    print("The input list is", input_list)
    print("the maximum value from the list is", find_max(input_list))    
    print("\n")
    
    # Case 5
    input_list = [5, 5, 7, 1, 10, -2]
    print("The input list is", input_list)
    print("the maximum value from the list is", find_max(input_list))