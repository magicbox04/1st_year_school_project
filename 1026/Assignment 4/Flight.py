from Airport import *


class Flight:
    def __init__(self, flightNo, origin, destination):
        # Check if origin and destination is Airport object
        if not (isinstance(origin, Airport) and isinstance(destination, Airport)) is True:
            raise TypeError("the origin and destination must be airport objects")

        # Initialize the instance variables _flightNo, _origin, and _destination based on the
        # Corresponding parameters in the constructor
        self._flightNo = flightNo
        self._origin = origin
        self._destination = destination

    def __repr__(self):
        # Return the flightNo, origin city,destination city, and domestic or international
        # ex.MCK533 from Toronto to Montreal {domestic}
        if self.isDomesticFlight():
            return 'Flight: ' + self._flightNo + ' from ' + self._origin.getCity() + ' to ' + \
                   self._destination.getCity() + ' {domestic}'
        else:
            return 'Flight: ' + self._flightNo + ' from ' + self._origin.getCity() + ' to ' + \
                   self._destination.getCity() + ' {international}'

    def __eq__(self, other):
        # Check if origin and destination are the same for both Flights.
        if (self._origin == other._origin) and (self._destination == other._destination):
            return True
        else:
            return False

    def getFlightNumber(self):
        # Getter that returns the Flight number code
        return self._flightNo

    def getOrigin(self):
        # Getter that returns the Flight origin
        return self._origin

    def getDestination(self):
        # Getter that returns the Flight destination
        return self._destination

    def isDomesticFlight(self):
        # Check if the flight is domestic
        if self._origin.getCountry() == self._destination.getCountry():
            return True
        else:
            return False

    def setOrigin(self, origin):
        # Setter that sets (updates) the Flight origin
        self.origin = origin

    def setDestination(self, destination):
        # Setter that sets (updates) the Flight destination
        self.destination = destination


