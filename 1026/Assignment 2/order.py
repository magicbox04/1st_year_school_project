#  Name: Robin Lee
#  Class: Computer Science 1026
#  Project Name: Pizza Ordering System
#  Professor: Professor Sarlo
#  Due Date: October 26th 2022
#  Finished Date: October 26th 2022


import pizzaReceipt


# function to order a pizza
def ordering():
    toppings = ("ONION", "SPINACH", "HAM", "TOMATO", "BROCCOLI", "BACON", "GREEN PEPPER", "PINEAPPLE",
                "GROUND BEEF", "MUSHROOM", "HOT PEPPER", "CHICKEN", "OLIVE", "PEPPERONI", "SAUSAGE")
    toppingChoice = []

    while True:
        size = str(input("Choose a size:  S, M, L, or XL: "))  # string input
        # change to upper case to ignore errors from lower and upper case input
        size = size.upper()
        if size == "S" or size == "M" or size == "L" or size == "XL":
            while True:
                topping = str(input('Type in one of our toppings to add it to your pizza. To see the list of '
                                    'toppings, enter "LIST". When you are done adding toppings, enter "X"\n'))
                # string input
                # change to upper case to ignore errors from lower and upper case input
                topping = topping.upper()
                if topping == "LIST":
                    print(toppings, sep="'")
                elif topping in toppings:
                    print("Added %s to your pizza" % topping)
                    # use .append to avoid the out of range error
                    toppingChoice.append(topping)
                elif topping == "X":
                    pizzaResult = (size, toppingChoice)
                    return pizzaResult
                else:
                    print("Invalid topping")

        else:
            print("")


# define the main function
def main():
    # empty list for the list of pizzas ordered
    pizzaList = []
    order = str(input("do you want to order a pizza?\n"))  # string input
    # change to upper case to ignore errors from lower and upper case input
    # send empty list if the input is no
    if order.upper() == "NO":
        pizzaReceipt.generateReceipt(pizzaList)
    else:
        while True:
            # use .append to avoid the out of range error
            pizzaList.append(ordering())
            continueOrder = str(input("Do you want to continue ordering? "))  # string Input
            # change to upper case to ignore errors from lower and upper case input
            if continueOrder.upper() == "Q" or continueOrder.upper() == "NO":
                pizzaReceipt.generateReceipt(pizzaList)
                break


# run main function
main()
