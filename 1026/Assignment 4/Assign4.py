#  Name: Robin Lee
#  Class: Computer Science 1026
#  Project Name: Air Travel
#  Professor: Professor Sarlo
#  Due Date: December 7th 2022
#  Finished Date: December 6th 2022

from Flight import *
from Airport import *

allAirports = []
flightList = []
allFlights = {}


def loadData(airportFileName, flightsFileName):
    # return false if the file is not found
    try:
        airportRead = open(airportFileName, "r", encoding='utf8')
        flightsRead = open(flightsFileName, "r", encoding='utf8')

    except:
        return False

    # Read the files and gather the information in a list
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

    # Create a list of dictionary with the list of information from the flight files
    for i in range(len(flightList)):
        if flightList[i].getOrigin().getCode() in allFlights:
            allFlights[flightList[i].getOrigin().getCode()].append(flightList[i])
        else:
            allFlights[flightList[i].getOrigin().getCode()] = [flightList[i]]

    return True


def getAirportByCode(code):
    # Return the Airport object that has the given code
    x = -1
    for i in range(len(allAirports)):
        if allAirports[i].getCode() == code:
            x = i

    if x == -1:
        return x
    else:
        return allAirports[x]


def findAllCityFlights(city):
    # Return a list that contains all Flight objects that involve the given city either as
    # the origin or the destination
    flightCityList = []
    for i in range(len(flightList)):
        if city == flightList[i].getOrigin().getCity() or city == flightList[i].getDestination().getCity():
            flightCityList.append(flightList[i])

    return flightCityList


def findAllCountryFlights(country):
    # Return a list that contains all Flight objects that involve the given country either
    # as the origin or the destination
    flightCityList = []
    for i in range(len(flightList)):
        if country == flightList[i].getOrigin().getCountry() or country == flightList[i].getDestination().getCountry():
            flightCityList.append(flightList[i])

    return flightCityList


def findFlightBetween(origAirport, destAirport):
    # Check if there is a direct and one hop flight from origAirport to destAirport
    singleHopList = []
    # Check direct flight from origAirport to destAirport
    for i in range(len(allFlights[origAirport.getCode()])):
        if destAirport == allFlights[origAirport.getCode()][i].getDestination():
            # return format is Direct Flight: origAirportCode to destAirportCode
            # i.e. Direct Flight: YYZ to ORD
            return "Direct Flight: " + origAirport.getCode() + " to " + destAirport.getCode()

    # Check direct and one hop flight from origAirport to destAirport
    for k in range(len(allFlights[origAirport.getCode()])):
        for j in range(len(allFlights[allFlights[origAirport.getCode()][k].getDestination().getCode()])):
            if destAirport == allFlights[allFlights[origAirport.getCode()][k].getDestination().getCode()] \
                    [j].getDestination():
                singleHopList.append(allFlights[origAirport.getCode()][k].getDestination().getCode())

    # return -1 if there are no direct and one hop flight from origAirport to destAirport
    # return the set of array of one hop flight from origAirport to destAirport
    if len(singleHopList) == 0:
        return -1
    else:
        return set(singleHopList)


# Take the given Flight object and look for the Flight object representing the return flight from that given flight.
def findReturnFlight(firstFlight):
    for i in range(len(allFlights[firstFlight.getDestination().getCode()])):
        if allFlights[firstFlight.getDestination().getCode()][i].getDestination() == firstFlight.getOrigin():
            return allFlights[firstFlight.getDestination().getCode()][i]

    return -1
