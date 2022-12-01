package com.fintech.ticket.service.impl;

import com.fintech.ticket.exceptions.NullEntityReferenceException;
import com.fintech.ticket.model.Ticket;
import com.fintech.ticket.repository.TicketRepository;
import com.fintech.ticket.service.TicketService;
import com.fintech.ticket.utils.UrlLinks;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("ticketServiceImpl")
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private RestTemplate restTemplate;

    @Override
    public Ticket createTicket(Ticket ticket) {
        if (ticket != null) {
            ticketRepository.saveAndFlush(ticket);
            restTemplate.postForEntity(UrlLinks.SEND_CREATE_PAYMENT, ticket, Ticket.class);

            return ticket;
        }
        throw new NullEntityReferenceException("Ticket cannot be 'null'");
    }

    @Override
    public Ticket readTicketByFlightId(long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ticket with id: " + id + " not found.")
        );
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = ticketRepository.getAllById();
        return allTickets.isEmpty() ? new ArrayList<>() : allTickets;
    }
}
