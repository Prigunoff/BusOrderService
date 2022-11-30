package com.fintech.ticket.service.impl;

import com.fintech.ticket.exceptions.NullEntityReferenceException;
import com.fintech.ticket.model.Flight;
import com.fintech.ticket.repository.FlightRepository;
import com.fintech.ticket.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("flightServiceImpl")
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    @Override
    public Flight createFlight(Flight flight) {
        if (flight != null) {
            flight.setTrainStartTime(LocalDateTime.now().plusDays(31));
            flightRepository.save(flight);
            return flight;
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public Flight readByFlightId(long id) {
        return flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight with id: " + id + " not found.")
        );
    }

    @Override
    public Flight updateFlight(Flight flight) {
        if (flight != null){
            return flightRepository.save(flight);
        }
            throw new NullEntityReferenceException("Flight cannot be 'null'.");
    }

    @Override
    public void deleteFlight(long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> allFlights = flightRepository.getAllById();
        return allFlights.isEmpty() ? new ArrayList<>() : allFlights;
    }
}
