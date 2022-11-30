package com.fintech.ticket.service;

import com.fintech.ticket.model.Flight;
import com.fintech.ticket.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Ticket readTicketByFlightId(long id);
    List<Ticket> getAllTickets();
}
