package com.fintech.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.order.enums.State;
import com.fintech.order.model.Payment;
import com.fintech.order.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentRestController.class)
class PaymentRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    PaymentService paymentService;

    @Test
    @DisplayName("Creating new payment ")
    public void create_New_Payment() throws Exception {
        Payment payment = new Payment(1L, State.randomStatus(), "Mock", "Mock", BigDecimal.valueOf(10));
        Mockito.when(paymentService.createPayment(Mockito.any(Payment.class))).thenReturn(payment);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("http://localhost:8081/api/payment/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(payment));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", is("Mock")))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(payment)));
    }

}