package com.fintech.ticket.controller;

import com.fintech.ticket.model.Flight;
import com.fintech.ticket.model.Ticket;
import com.fintech.ticket.service.FlightService;
import com.fintech.ticket.service.TicketService;
import com.fintech.ticket.utils.PaymentStatus;
import com.fintech.ticket.utils.ResponseDto;
import com.fintech.ticket.utils.TicketDTO;
import com.fintech.ticket.utils.UrlLinks;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "api/flight/ticket")
public class TicketRestController {
    private FlightService flightService;
    private TicketService ticketService;
    private RestTemplate restTemplate;


    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getOneTicket(@PathVariable("id") Long flightId) {
        Ticket ticket = ticketService.readTicketByFlightId(flightId);
        ResponseDto responseDto = new ResponseDto();

        ResponseEntity<PaymentStatus> responseEntity = restTemplate
                .getForEntity(UrlLinks.GET_INFO_PAYMENT + ticket.getId(),
                        PaymentStatus.class);
        PaymentStatus paymentStatus = responseEntity.getBody();

        responseDto.setTicket(ticket);
        responseDto.setPaymentStatus(paymentStatus);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<TicketDTO> createTicketForFlight(@PathVariable("id") Long flightId, @RequestBody Ticket ticket) {
        Flight flight = flightService.readByFlightId(flightId);
        TicketDTO ticketDTO = new TicketDTO();
        if (flight.getAvailableTickets() <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        flight.setAvailableTickets(flight.getAvailableTickets() - 1);
        flightService.updateFlight(flight);

        ticket.setFlight_plan(flightService.readByFlightId(flightId));
        ticketService.createTicket(ticket);
        ticketDTO.setId(ticket.getId());
        restTemplate.postForEntity(UrlLinks.SEND_CREATE_PAYMENT, ticket, Ticket.class); //Sends post to Payment controller with data from ticket

        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
    }
}
