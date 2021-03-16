"""This program asks the user to enter their first name and prints it in all 
capital letters.

Author: Shakib Absar
Section: 001
Student Number: 20245608
"""
def get_input() -> str:
    """ask the user to input their name and repeat until a non-numeric and
    non-empty string has been entered and return that string as output.
    """
    s = ""
    repeat = True
    print("Please enter your name:",end=" ")
    # This while loop repeats until the user enters a proper name
    while repeat:
        s = input()
        if s.isalpha() and len(s) > 0:
            repeat = False
        else:
            print("Please try again and enter your name.")
    return s


# Main program starts from here.
repeat = 'y'
#This while loop runs atleast once and then repeats if the user wishes to.
while repeat == 'Y' or repeat == 'y':
    name = get_input()
    name = name.upper()
    print("Your name in all capital letters is " + name)
    repeat = input("Would you like to do it again (y/n)? ")
