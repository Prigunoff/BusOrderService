package com.fintech.ticket.utils;

import com.fintech.ticket.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Ticket ticket;
    private PaymentStatus paymentStatus;
}
