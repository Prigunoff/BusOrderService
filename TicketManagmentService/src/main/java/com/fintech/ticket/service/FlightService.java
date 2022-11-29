package com.fintech.ticket.service;

import com.fintech.ticket.model.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);

    Flight readByFlightId(long id);

    Flight updateFlight(Flight flight);

    void deleteFlight(long id);

    List<Flight> getAllFlights();

}
