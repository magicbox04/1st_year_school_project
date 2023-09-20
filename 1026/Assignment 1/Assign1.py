#  Name: Robin Lee
#  Class: Computer Science 1026
#  Project Name:
#  Professor: Professor Sarlo
#  Due Date: October 5th 2022
#  Finished Date: September 28th 2022

#  preassigned variable to save the net value of expense
netPreExp = 0
netCurrExp = 0
# gets input for the year
yearNum = int(input("Please enter the year that you want to calculate the personal interest  rate for : "))  # int cast
#  gets input for the number of expenditure categories
expCat = int(input("please enter the number of expenditure categories: "))  # cast as int

#  For loop for the multiple inputs for expenses
#  Calculates the total previous expense and current expense
for x in range(expCat):
    netPreExp += int(input("please enter expenses for previous year: "))  # cast as int
    netCurrExp += int(input("please enter expenses for year or interest: "))  # cast as int

#  calculates the personal inflation rate
inflation = float(((netCurrExp - netPreExp) / netCurrExp) * 100)  # cast as float

#  Determines the inflation type
if inflation < 3:
    inflationType = "low"
elif 3 <= inflation < 5:
    inflationType = "moderate"
elif 5 <= inflation < 10:
    inflationType = "high"
elif inflation >= 10:
    inflationType = "hyper"

#  printing the result
print(f"personal inflation rate for %d is %.1f%%" % (yearNum, inflation))
print("type of inflation: %s" % inflationType)
