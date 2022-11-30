package com.fintech.order.service.impl;

import com.fintech.order.exceptions.NullEntityReferenceException;
import com.fintech.order.model.Payment;
import com.fintech.order.repository.PaymentRepository;
import com.fintech.order.service.PaymentService;
import com.fintech.order.utils.PaymentStatusDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("paymentServiceImpl")
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        if (payment != null) {
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

    @Override
    public Payment updatePayment(Payment payment) {
        if (payment != null) {
            return paymentRepository.save(payment);
        }
        throw new NullEntityReferenceException("Payment cannot be 'null'.");
    }
    @Override
    public Payment readByLastName(String lastName) {
        Payment payment = paymentRepository.findByLastName(lastName);
        if (payment == null) {
            throw new NullEntityReferenceException("Payment for " + lastName + " not found");
        }
        return payment;
    }
    @Override
    public PaymentStatusDto mapToDto(Payment payment) {
        PaymentStatusDto paymentStatusDto = new PaymentStatusDto();
        paymentStatusDto.setStatus(payment.getPaymentStatus());
        return paymentStatusDto;
    }
}
