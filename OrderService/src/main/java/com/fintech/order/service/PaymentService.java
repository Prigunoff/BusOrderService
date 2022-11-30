package com.fintech.order.service;

import com.fintech.order.model.Payment;
import com.fintech.order.utils.PaymentStatusDto;

public interface PaymentService {
    Payment createPayment(Payment payment);

    Payment readByPaymentId(long id);

    Payment updatePayment(Payment payment);
    Payment readByLastName(String lastName);
    PaymentStatusDto mapToDto(Payment payment);
}
