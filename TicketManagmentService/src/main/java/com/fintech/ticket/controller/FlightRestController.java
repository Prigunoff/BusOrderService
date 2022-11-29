package com.fintech.ticket.controller;

import com.fintech.ticket.model.Flight;
import com.fintech.ticket.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "api/flight")
public class FlightRestController {
    private final FlightService flightService;

    @GetMapping("{id}")
    public  ResponseEntity<Flight> getOneFlight(@PathVariable("id") Long flId){
        return new ResponseEntity<>(flightService.readByFlightId(flId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Flight>>getAllFlights(){
        return new ResponseEntity<>(this.flightService.getAllFlights(), HttpStatus.OK);
    }
}
