"""This program compares the efficiency of insertion, selection and, bubble 
sorting algorithm.
This program asks the user to enter a number for the length of the lists to be 
used in the comparison. 
It then uses the list_generation module to create 3 lists in ascending, 
descending and, random order.
It uses the quadratic_sorts module to to get the data on how many times the 
inner loop and outer loop is executed and how many swaps took place for each 
sorting algorithm and each list.
It outputs the data in a user friendly manner.
"""
from typing import List
import list_generator as lg
import quadratic_sorts as qs

def main():
    
    length = int(input("\nPlease enter the length of input lists: "))
    
    best_case = lg.list_ascending(length)
    worse_case = lg.list_descending(length)
    random_case = lg.list_randomized(length)
    
    print("\n\n")
    print(f"Best-case example: {best_case}")
    print(f"Worse-case example: {worse_case}")
    print(f"Randomized example: {random_case}")
    
    #The name and the function call of each sorting algorithm is stored in an 
    #array, so a for loop can use an iterator to go through the items easily.
    sort_types = ["insertion", "selection", "bubble"]
    sort_functions = [qs.insertion, qs.selection, qs.bubble]
    
    #Each iteration of this for loop collects and outputs the data of a single 
    #sorting algorithm. It iterates 3 times because we have 3 sorting algorithm.
    #It uses the sort_types and sort_functions as parallel arrays.
    for i in range(3):
        data = []
            
        data.append(sort_functions[i](best_case.copy()))
        data.append(sort_functions[i](worse_case.copy()))
        data.append(sort_functions[i](random_case.copy()))
                
        output_results(sort_types[i], data)
    

def output_results(sort_type: str, data: List[List[int]]):
    """This function takes a sort_type and a 3X3 matrix of integer data and 
    prints them in a readable way.
    """
    print(f"\n\nUsing {sort_type} sort:\n")
    
    print("\t\t# of outer loops\t# of inner loops\t# of swaps")
    print(f"Best-case\t\t{data[0][0]}\t\t\t{data[0][1]}\t\t\t{data[0][2]}")
    print(f"Worse-case\t\t{data[1][0]}\t\t\t{data[1][1]}\t\t\t{data[1][2]}")
    print(f"Randomized-case\t\t{data[2][0]}\t\t\t{data[2][1]}\t\t\t{data[2][2]}")    

if __name__ == "__main__":
    # Execution section
    main()