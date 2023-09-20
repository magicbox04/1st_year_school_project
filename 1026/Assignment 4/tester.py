from Flight import *
from Airport import *

allAirports = []
flightList = []
allFlights = {}
airportFileName = "airports.txt"
flightsFileName = "flights.txt"


def loadData(airportFileName, flightsFileName):
    # quit if the file is not found
    try:
        airportRead = open(airportFileName, "r", encoding='utf8')
        flightsRead = open(flightsFileName, "r", encoding='utf8')

    except:
        return False

    for line in airportRead:  # line == YYZ,Canada,Toronto\n
        line = line.strip()  # line == YYZ,Canada,Toronto
        line = line.replace(" ", "")
        line = line.replace("\t", "")
        lineSplit = line.split(",")  # lineSplit == ['YYZ', 'Canada', 'Toronto']
        allAirports.append(Airport(lineSplit[0], lineSplit[2], lineSplit[1]))

    for line in flightsRead:  # line == XJX595,LAX,CPT\n
        line = line.strip()  # line == XJX595,LAX,CPT
        line = line.replace(" ", "")
        line = line.replace("\t", "")
        lineSplit = line.split(",")  # lineSplit == ['XJX595', 'LAX', 'CPT']
        flightList.append(Flight(lineSplit[0], getAirportByCode(lineSplit[1]), getAirportByCode(lineSplit[2])))

    for i in range(len(flightList)):
        if flightList[i].getOrigin().getCode() in allFlights:
            allFlights[flightList[i].getOrigin().getCode()].append(flightList[i])
        else:
            allFlights[flightList[i].getOrigin().getCode()] = [flightList[i]]
    return True

def getAirportByCode(code):
    x = -1
    for i in range(len(allAirports)):
        if allAirports[i].getCode() == code:
            x = i
    if x == -1:
        return x
    else:
        return allAirports[x]


def findAllCityFlights(city):
    flightCityList = []
    for i in range(len(flightList)):
        if city == flightList[i].getOrigin().getCity() or city == flightList[i].getDestination().getCity():
            flightCityList.append(flightList[i])
    return flightCityList


def findAllCountryFlights(country):
    flightCityList = []
    for i in range(len(flightList)):
        if country == flightList[i].getOrigin().getCountry() or country == flightList[i].getDestination().getCountry():
            flightCityList.append(flightList[i])
    return flightCityList


def findFlightBetween(origAirport, destAirport):
    singleHopList = []
    for i in range(len(allFlights[origAirport.getCode()])):
        if destAirport == allFlights[origAirport.getCode()][i].getDestination():
            return "Direct Flight: " + origAirport.getCode() + " to " + destAirport.getCode()
    for k in range(len(allFlights[origAirport.getCode()])):
        for j in range(len(allFlights[allFlights[origAirport.getCode()][k].getDestination().getCode()])):
            if destAirport == allFlights[allFlights[origAirport.getCode()][k].getDestination().getCode()][j].getDestination():
                singleHopList.append(allFlights[origAirport.getCode()][k].getDestination().getCode())
    if len(singleHopList) == 0:
        return -1
    else:
        return set(singleHopList)


# loadData(airportFileName, flightsFileName)
# print(findFlightBetween(getAirportByCode("PVG"), getAirportByCode("YOW")))
# origAirport = getAirportByCode("PVG")
# for i in range(len(allFlights[origAirport.getCode()])):
#     if (allFlights[origAirport.getCode()][i].getDestination()) == (getAirportByCode("YOW")):
#         print ("yes")
#     else:
#         print ("No")

#getAirportByCode("PVG").getCode()
#print(allFlights)
#print(findFlightBetween(origAirport, destAirport))
