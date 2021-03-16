from typing import List

def hopping_game(n: int)-> List[str]:
    """Return a list of all possible hopping paths with n number of squares.
    Hops can be consited of either 1 square or 2 squares. 
    """
    #Base Cases for this recursive function is n = 0, 1 and, 2
    if n == 0:
        return []
    elif n == 1:
        return ["0-1"]
    elif n == 2:
        return ["0-1-2", "0-2"]
    #The recursive potion of the function begins here
    else:
        output = []

        #This for loop creates all possible paths if the initial hop covers only 
        #one square
        for path in hopping_game(n-1):
            new_path = "0"
            #This inner for loop offsets each step by 1 and adds it to the 
            #new_path
            for step in path.split("-"):
                new_path += "-"+str(int(step) + 1)
            output.append(new_path)
        
        #This for loop does the exact same thing as the last for loop except the
        #hop covers 2 squares
        for path in hopping_game(n-2):
            new_path = "0"
            for step in path.split("-"):
                new_path += "-"+str(int(step) + 2)
            output.append(new_path)
        
        return output
    
#Model testing code
if __name__ == "__main__":
    
    #Case 1
    print("\n")
    print("Model Testing Case 1")
    n = 1
    print("n = 1")
    print("Output - ")
    print(hopping_game(n))
    
    #Case 2
    print("\n")
    print("Model Testing Case 2")
    n = 3
    print("n = 1")
    print("Output - ")
    print(hopping_game(n))

    #Case 3
    print("\n")
    print("Model Testing Case 3")
    n = 5
    print("n = 1")
    print("Output - ")
    print(hopping_game(n))

    