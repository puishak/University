from typing import List
import random

MIN = 0.0
MAX = 100.0

def list_ascending(length: int) -> List[float]:
    """Return a list of randomized floats in ascending order with 'length' as
    the size of the list
    """
    output = []
    temp = MIN
    
    for i in range(length):
        output.append(rand(temp, MAX))
        temp = output[i]
    
    return output



def list_descending(length: int) -> List[float]:
    """Return a list of randomized floats in descending order with 'length' as
    the size of the list
    """
    output = []
    temp = MAX
    
    for i in range(length):
        output.append(rand(MIN, temp))
        temp = output[i]
    
    return output


def list_randomized(length: int) -> List[float]:
    """Return a list of randomized floats with 'length' as the size of the list
    """    
    output = []
    
    for i in range(length):
        output.append(rand(MIN, MAX))
    
    return output



def rand(lower: float, upper: float) -> float:
    """Return a random number between lower and upper rounded to 2 decimal
    places.
    """
    return round(random.uniform(lower, upper), 2)
