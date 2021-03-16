"""This program utilizes friendship network data to compute and output the
number of friends each person has.

Author: Shakib Absar
Section: 001
Student Number: 20245608
"""
FILE_NAME = "friendship.txt"

friendship_data = {}
file = open(FILE_NAME, "r")

# This section extracts the data from the file and stores it in a dict
line = file.readline()
while line != "":
    data = (line.rstrip().split())
    
    for person in data:
        if person in friendship_data:
            friendship_data[person] += 1
        else:
            friendship_data[person] = 1
            
    line = file.readline()
file.close()

#This section outputs the data in the proper format
for name in friendship_data:
    print(f"{name} has {friendship_data[name]} friends.")