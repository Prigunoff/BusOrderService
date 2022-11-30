package com.fintech.order.service.impl;

import com.fintech.order.exceptions.NullEntityReferenceException;
import com.fintech.order.model.Customer;
import com.fintech.order.model.Payment;
import com.fintech.order.repository.PaymentRepository;
import com.fintech.order.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service("paymentServiceImpl")
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    @Override
    public Payment createPayment(Payment payment) {
        if(payment != null){
            paymentRepository.save(payment);
            return payment;
        }
        throw new NullEntityReferenceException("Payment cannot be 'null' ");
    }

    @Override
    public Payment readByPaymentId(long id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Payment with id: " + id + " not found.")
        );
    }

}
