package com.fintech.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.ticket.model.Flight;
import com.fintech.ticket.service.FlightService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightRestController.class)
class FlightRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    FlightService flightService;

    @Test
    @DisplayName("Getting all flights ")
    public void get_All_Flights_Success() throws Exception {
        Flight flight = new Flight(1L, "Test", "Test", LocalDateTime.now(), 10, new ArrayList<>());
        Flight flight2 = new Flight(2L, "Test2", "Test2", LocalDateTime.now(), 12, new ArrayList<>());
        List<Flight> flightList = new ArrayList<>(Arrays.asList(flight, flight2));

        Mockito.when(flightService.getAllFlights()).thenReturn(flightList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/admin/flight/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fromCity", is("Test")))
                .andExpect(jsonPath("$[0].toCity", is("Test")))
                .andExpect(jsonPath("$[1].fromCity", is("Test2")))
                .andExpect(jsonPath("$[1].toCity", is("Test2")));

    }

    @Test
    @DisplayName("Creating new flight ")
    public void create_New_Flight() throws Exception {
        Flight flight = new Flight(2L, "Test", "Test", null, 10, new ArrayList<>());
        Mockito.when(flightService.createFlight(Mockito.any(Flight.class))).thenReturn(flight);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("http://localhost:8080/api/admin/flight")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(flight));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.fromCity", is("Test")))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(flight)));
    }
    @Test
    public void delete_Flight() throws Exception {
    long id = 10L;
    FlightService spy = Mockito.spy(flightService);
        Mockito.doNothing().when(spy).deleteFlight(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/admin/flight/10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        Mockito.verify(flightService,Mockito.times(1)).deleteFlight(id);
    }
}