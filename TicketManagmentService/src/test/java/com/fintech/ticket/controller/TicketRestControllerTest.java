package com.fintech.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.ticket.model.Flight;
import com.fintech.ticket.model.Ticket;
import com.fintech.ticket.service.FlightService;
import com.fintech.ticket.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketRestController.class)
class TicketRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    FlightService flightService;
    @MockBean
    TicketService ticketService;
    @Test
    @DisplayName("Getting all tickets ")
    public void get_All_Tickets_Success() throws Exception {
        Flight flight = new Flight(1L, "Test", "Test", LocalDateTime.now(), 10, new ArrayList<>());
        Ticket ticket = new Ticket(1L,"Mock","Mock",flight);
        Ticket ticket2 = new Ticket(2L,"Mock2","Mock2",flight);

        List<Ticket> tickets = new ArrayList<>(Arrays.asList(ticket, ticket2));

        Mockito.when(ticketService.getAllTickets()).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/flight/ticket/admin/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Mock")))
                .andExpect(jsonPath("$[0].lastName", is("Mock")))
                .andExpect(jsonPath("$[1].firstName", is("Mock2")))
                .andExpect(jsonPath("$[1].lastName", is("Mock2")));

    }

}