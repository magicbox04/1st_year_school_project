#  Name: Robin Lee
#  Class: Computer Science 1026
#  Project Name: Universities Ranking
#  Professor: Professor Sarlo
#  Due Date: November 20th 2022
#  Finished Date: November 18th 2022


# 1. Universities Count
def getTotalNumber(uniList):
    return len(uniList)


# 2. Available Countries
def availableCountry(uniList):
    countryList = []

    for x in range(len(uniList)):
        countries = uniList[x][2]  # uniList[x][2] == country name
        # check duplicates
        if countries.upper() not in countryList:
            countryList.append(countries.upper())

    return countryList


# 3. Available Continents
def availableContinents(capitalList, uniList):
    continentsList = []
    # get the countryList from availableCountry function
    countryList = availableCountry(uniList)

    for x in range(len(countryList)):
        for y in range(len(capitalList)):
            # check if country in the country list is also in the capital List
            if countryList[x] == capitalList[y][0]:  # capitalList[y][0] == country names
                # check for duplicates
                if capitalList[x][2] not in continentsList:  # capitalList[x][2] == continent names
                    continentsList.append(capitalList[x][2])

    return continentsList


# 4. The Universities With Top International Ranking
def topInternationalRanking(uniList, selectedCountry):
    for x in range(len(uniList)):
        # find the university with the highest ranking inside the country
        if uniList[x][2] == selectedCountry:
            # return the rank and name
            return uniList[x][0], uniList[x][1]  # uniList[x][0] == world rank, uniList[x][1] == school name


# 5. The University With Top National Rank
def topNationalRanking(uniList, selectedCountry):
    nationalList = []
    rank = len(uniList)  # highest rank possible

    # get list of names and national rank of universities within the selected country
    for x in range(len(uniList)):
        if uniList[x][2] == selectedCountry:
            nationalList.append((uniList[x][1], uniList[x][3]))  # uniList[x][3] == national rank

    # find the university with the highest rank
    for x in range(len(nationalList)):
        if int(rank) > int(nationalList[x][1]):
            rank = nationalList[x][1]
            name = nationalList[x][0]

    return rank, name


# 6. The Average Score
def averageScore(uniList, selectedCountry):
    averageList = []
    totalScore = 0

    # get all the scores of the universities inside the selected country
    for x in range(len(uniList)):
        if uniList[x][2] == selectedCountry.upper():
            averageList.append(float(uniList[x][4]))  # uniList[x][4] == scores

    # get the total score of all numbers in averageList
    for number in averageList:
        totalScore += number

    average = (totalScore / len(averageList))  # calculate the average
    average = round(average, 2)  # round to 2 decimal place
    return average


# 7. The Continental Relative Score
def continentRelativeScore(uniList, selectedCountry, capitalList):
    countryList = []
    highestScore = 0
    for x in range(len(capitalList)):
        # find the continent of the selected country
        if capitalList[x][0] == selectedCountry.upper():
            continent = capitalList[x][2]
            break

    # get all the countries within the continent
    for x in range(len(capitalList)):
        if capitalList[x][2] == continent:
            if capitalList[x][0] not in countryList:
                countryList.append(capitalList[x][0])

    # get the highest score within the continent
    for x in range(len(uniList)):
        if uniList[x][2] in countryList:
            if float(uniList[x][4]) > highestScore:
                highestScore = float(uniList[x][4])

    # find the relative score using average and highest score
    average = averageScore(uniList, selectedCountry)
    finalRelativeScore = (average / highestScore) * 100
    finalRelativeScore = round(finalRelativeScore, 2)
    return continent, average, highestScore, finalRelativeScore


# 8. The Capital City
def capitalCity(capitalList, selectedCountry):
    capital = ""
    for x in range(len(capitalList)):
        # find the selected country within the capital list and get the capital of that country
        if capitalList[x][0] == selectedCountry.upper():
            return capitalList[x][1]  # capitalList[x][1] == capital name


# 9. The University That Holds The Capital Name
def uniWithCapitalName(uniList, capital):
    uniWithCapitalNameList = []
    # create a list of universities that include the capital in their name
    for x in range(len(uniList)):
        if capital in uniList[x][1]:
            uniWithCapitalNameList.append(uniList[x][1])

    return uniWithCapitalNameList


# getInformation to process all the information
def getInformation(selectedCountry, rankingFileName="TopUni.csv", capitalsFileName="capitals.csv"):
    tempList = []
    capitalList = []
    uniList = []
    selectedCountry = selectedCountry.upper()
    # quit if the file is not found
    try:
        capital = open(capitalsFileName, "r", encoding='utf8')
        rankUni = open(rankingFileName, "r", encoding='utf8')
    except FileNotFoundError:
        output = open("output.txt", "w", encoding='utf8')
        output.write("file not found")
        output.close()
        quit()

    # readline() to get rid of the header
    capital.readline()
    rankUni.readline()

    # stores all the needed information from the capitals.csv
    # strip the \n and split them using ","
    for line in capital:  # line == Jordan,Amman,31.95,35.933333,JO,Asia\n
        line = line.strip()  # line == Jordan,Amman,31.95,35.933333,JO,Asia
        lineSplit = line.split(",")  # lineSplit == ['Jordan', 'Amman', '31.95', '35.933333', 'JO', 'Asia']
        tempList.append(lineSplit)

    for x in range(len(tempList)):
        countryName = tempList[x][0]
        capitalName = tempList[x][1]
        continentName = tempList[x][5]
        capitalList.append((countryName.upper(), capitalName.upper(), continentName.upper()))

    tempList.clear()

    # stores all the needed information from the TopUni.csv
    # Same process with capitals.csv
    for line in rankUni:
        line = line.strip()
        lineSplit = line.split(",")
        tempList.append(lineSplit)

    for x in range(len(tempList)):
        worldRank = tempList[x][0]
        institutionName = tempList[x][1]
        countryName = tempList[x][2]
        nationalRank = tempList[x][3]
        score = tempList[x][8]
        uniList.append((worldRank, institutionName.upper(), countryName.upper(), nationalRank, score))

    tempList.clear()

    # That stores all the information needed to answer the questions
    returnList = [getTotalNumber(uniList), availableCountry(uniList), availableContinents(capitalList, uniList),
                  topInternationalRanking(uniList, selectedCountry), topNationalRanking(uniList, selectedCountry),
                  averageScore(uniList, selectedCountry), continentRelativeScore(uniList, selectedCountry, capitalList),
                  capitalCity(capitalList, selectedCountry),
                  uniWithCapitalName(uniList, capitalCity(capitalList, selectedCountry))]
    # close the opened file
    capital.close()
    rankUni.close()

    # write the information from getInformation on output.txt
    output = open("output.txt", "w", encoding='utf8')

    # 1 University Count
    output.write("Total number of universities => " + str(returnList[0]) + "\n")

    # 2 Available countries
    printList = ', '.join(returnList[1])
    output.write("Available countries => " + printList + "\n")

    # 3 Available continents
    printList = ', '.join(returnList[2])
    output.write("Available continents => " + printList + "\n")

    # 4 The university with top international rank
    output.write("At international rank => " + returnList[3][0] + " the university name is => " + returnList[3][1] +
                 "\n")

    # 5 The university with top national rank
    output.write("At national rank => " + returnList[4][0] + " the university name is => " +
                 returnList[4][1] + "\n")

    # 6 The average score
    output.write("The average score => " + str(returnList[5]) + "%" + "\n")

    # 7 The continent relative score
    output.write("The relative score to the top university in " + returnList[6][0] + " is => (" +
                 str(returnList[6][1]) + "/" + str(returnList[6][2]) +
                 ") x 100% = " + str(returnList[6][3]) + "%" + "\n")

    # 8 The capital city
    output.write("The capital is => " + str(returnList[7]) + "\n")

    # 9 The universities that hold capital name
    output.write("The universities that contain the capital name => \n")
    for x in range(len(returnList[8])):
        output.write("\t" + "#" + str(x + 1) + " " + returnList[8][x] + "\n")

    # Close output.txt
    output.close()
