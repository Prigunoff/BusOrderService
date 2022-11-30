package com.fintech.order.service;

import com.fintech.order.model.Customer;
import com.fintech.order.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);

    Payment readByPaymentId(long id);

}
