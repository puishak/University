

def find_first_b(input_string: str) -> int:
    """Return the index of the first "b".
    input_string must contain only "a"s and "b"s and 
    all the "a"s must be to the left of all the "b"s
    """
    #The base cases for this recursive function is if input_string is empty or 
    #the first letter of it is "b"
    if len(input_string) == 0:
        return None
    elif input_string[0] == "b":
        return 0
    #The recursive potion of the function begins here
    else:
        rec = find_first_b(input_string[1:])
        if rec == None:
            return None
        else:
            return rec + 1