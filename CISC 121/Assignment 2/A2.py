"""This program can perform 3 tasks: calculate factorial, generate a list of
multiples and find maximum number in a list of integer. It asks the user to 
choose from the 3 tasks and completes them using fun_math module and then 
repeats if the user wishes.

Author: Shakib Absar
Section: 001
Student Number: 20245608
"""
import fun_math as fm

def main():
    
    # This while loop contains the whole program and is designed to run atleast
    # once. It repeats as many times the user enters 'r' at the end of
    # each task.
    repeat = True
    while repeat:
        
        print("\n\nPlease choose your task:", 
              "1 - Calculate factorial", 
              "2 - Generate a list of multiples", 
              "3 - Find max number in a list", sep="\n")
        
        # This while loop makes sure that the user succesfully chooses one of 
        # the task and then calls the function responsible for that task.
        input_okay = False
        while not input_okay:
            task = input(" -> ")
            
            if task == "1":
                task1()
                input_okay = True
            elif task == "2":
                task2()
                input_okay = True
            elif task == "3":
                task3()
                input_okay = True
            else:
                #This block is run if the user fails to choose a task.
                print("Please choose from the option 1, 2, or 3")
                input_okay = False
        
        print("\n\npress 'r' if you would like to perform another " + 
              "task: ", end="")
        if input() != "r":
            repeat = False


def task1():
    print("\n\nCalculate factorial\n")
    
    # This code block makes sure the input value for 'x' is a positive integer
    x = 0
    while not(x > 0):
        try:
            x = int(input("Please enter a postive integer: "))
        except ValueError:
            print("ERROR! You must enter an integer value.")
    
    # output
    print("factorial of", x, "is", fm.cal_factorial(x))


def task2():
    print("\n\nGerenrate a list of multiples\n")

    # This code block makes sure the input value for 'number' is a 
    # non-negative integer 
    number = -1
    while not(number >= 0):
        try:
            number = int(input("Please enter a non-negative number " +
                               "to be multiplied: "))
        except ValueError:
            print("ERROR! You must enter an integer value.")
        
    # This code block makes sure the input value for 'length' is a positive 
    # integer    
    length = 0
    while not(length > 0):
        try:
            length = int(input("Please enter a postive integer " +
                               "for the length of the list: "))
        except ValueError:
            print("ERROR! You must enter an integer value.")
    
    # output
    print(length, "multiples of", number, "are", 
          fm.list_multiples(number, length))
    

def task3():
    print("\n\nFind maximum value from a list\n")
    
    list_numbers = []

    # This code block makes sure the input value for 'length' is a positive 
    # integer    
    length = 0
    while not(length > 0):
        try:
            length = int(input("Please enter a postive integer " +
                               "for the length of the list: "))
        except ValueError:
            print("ERROR! You must enter an integer value.")
    
        
    # This for loop asks the user for all the value for the input list and
    # makes sure all the input are integers value and then stores the values
    # in list_number
    for i in range(length):
        input_okay = False
        while not input_okay:
            try:
                number = int(input(str(i+1) + " -> "))
                list_numbers.append(number)
                input_okay = True
            except ValueError:
                print("ERROR! only integers values are allowed to be in " +
                      "the list.")
    
    # output
    print("Maximum value from the list is", fm.find_max(list_numbers))
    

if __name__ == "__main__":
    # Execution section
    main()