# Function to generate the receipt
def generateReceipt(pizzaOrder):
    # tuple for the price of pizza
    pizzaCost = (7.99, 9.99, 11.99, 13.99)
    AdditionalToppingCost = (0.50, 0.75, 1.00, 1.25)
    totalCost = 0.00

    # if the pizzaOrder is empty, return 0 and end the code
    if len(pizzaOrder) == 0:
        print("You did not order anything")
    else:
        # if the pizza order is not empty, run the receipt
        print("your order: ")

        for pizzaNum in range(len(pizzaOrder)):
            # print the size of the pizza
            # used tuple to change values easily later
            if pizzaOrder[pizzaNum][0] == "S":
                print("Pizza {}: S {:>21.2f}".format(pizzaNum + 1, pizzaCost[0]))
                totalCost += pizzaCost[0]
            elif pizzaOrder[pizzaNum][0] == "M":
                print("Pizza {}: M {:>21.2f}".format(pizzaNum + 1, pizzaCost[1]))
                totalCost += pizzaCost[1]
            elif pizzaOrder[pizzaNum][0] == "L":
                print("Pizza {}: L {:>21.2f}".format(pizzaNum + 1, pizzaCost[2]))
                totalCost += pizzaCost[2]
            elif pizzaOrder[pizzaNum][0] == "XL":
                print("Pizza {}: XL {:>20.2f}".format(pizzaNum + 1, pizzaCost[3]))
                totalCost += pizzaCost[3]

            # print the name of toppings
            for toppingNum in range(len(pizzaOrder[pizzaNum][1])):
                print("- %s" % pizzaOrder[pizzaNum][1][toppingNum])

            # if the topping number exceeds 2, add additional price
            # used tuples to easily change values later when edit is required
            for x in range(len(pizzaOrder[pizzaNum][1]) - 3):
                if pizzaOrder[pizzaNum][0] == "S":
                    print("Extra Topping (S) {toppingCost:>14.2f}".format(toppingCost = AdditionalToppingCost[0]))
                    totalCost += AdditionalToppingCost[0]
                elif pizzaOrder[pizzaNum][0] == "M":
                    print("Extra Topping (M) {toppingCost:>14.2f}".format(toppingCost = AdditionalToppingCost[1]))
                    totalCost += AdditionalToppingCost[1]
                elif pizzaOrder[pizzaNum][0] == "L":
                    print("Extra Topping (L) {toppingCost:>14.2f}".format(toppingCost = AdditionalToppingCost[2]))
                    totalCost += AdditionalToppingCost[2]
                else:
                    print("Extra Topping (XL) {toppingCost:>13.2f}".format(toppingCost = AdditionalToppingCost[3]))
                    totalCost += AdditionalToppingCost[3]

        # print tax and total
        print("Tax: {:>27.2f}".format(totalCost * 0.13))  # 13%
        print("Total: {:>25.2f}".format(totalCost * 1.13))  # total + 13% which is 113% of total
