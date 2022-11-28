package com.fintech.ticket.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Flight {
    private Integer id;
    private String fromCity;
    private String toCity;
    private LocalDateTime trainStartTime;
    private Integer availableTickets;
}
