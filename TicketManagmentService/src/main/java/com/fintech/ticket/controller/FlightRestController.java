package com.fintech.ticket.controller;

import com.fintech.ticket.model.Flight;
import com.fintech.ticket.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/admin/flight")
public class FlightRestController {
    private final FlightService flightService;
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return new ResponseEntity<>(flightService.createFlight(flight),HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public  ResponseEntity<Flight> getOneFlight(@PathVariable("id") Long flId){
        return new ResponseEntity<>(flightService.readByFlightId(flId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Flight>>getAllFlights(){
        return new ResponseEntity<>(this.flightService.getAllFlights(), HttpStatus.OK);
    }
}
