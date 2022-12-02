package com.fintech.ticket.controller;

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


    @GetMapping("/admin/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        log.info("TicketController:GET:/all has been reached");
        return new ResponseEntity<>(tickets, HttpStatus.OK);
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
        log.info("TicketController:GET:getOneTicket():Reading info about ticket. Id: {}",ticket.getId());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<TicketDTO> createTicketForFlight(@PathVariable("id") Long flightId, @RequestBody Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        flightService.updateFlight(flightService.readByFlightId(flightId));

        ticket.setFlight_plan(flightService.readByFlightId(flightId));
        ticketService.createTicket(ticket);
        ticketDTO.setId(ticket.getId());

        log.info("Created ticket with id: {}" + " ,for flight id: {}",ticket.getId(),flightService.readByFlightId(flightId).getId());
        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
    }
}
