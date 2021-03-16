"""This program determines an integer value the user had in mind. It first asks 
the user to enter the lower and upper bounds of the rangethe integer belongs to,
and then presents a series of questions “Is the number greater than x?” with 
yes/no answers to the user in order to determine the integer value that the user
had in mind. 
"""

def main():
    
    lower = int(input("Please enter the lower bound: "))
    upper = int(input("Please enter the upper bound: "))
    
    while lower < upper:
        mid = (upper + lower) // 2
        
        answer = input(f"Is the number greater than {mid} (Y/N)? ")
        if answer == "y" or answer == "Y":
            lower = mid + 1
            mid += 1 
            #This is to offset mid by 1 in the last iteration when there is only
            #two numbers left and mid is the smaller number.
        elif answer == "n" or answer == "N":
            upper = mid
            
    print(f"\nThe number you had in mind is {mid}")
    

if __name__ == "__main__":
    # Execution section
    main()