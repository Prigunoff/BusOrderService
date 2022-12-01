package com.fintech.order.service.impl;

import com.fintech.order.enums.State;
import com.fintech.order.exceptions.NullEntityReferenceException;
import com.fintech.order.model.Payment;
import com.fintech.order.repository.PaymentRepository;
import com.fintech.order.service.PaymentService;
import com.fintech.order.utils.PaymentStatusDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service("paymentServiceImpl")
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        if (payment != null) {
            payment.setMoney(BigDecimal.valueOf(0));
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
            Payment payment1 = readByLastName(payment.getLastName());
            payment1.setMoney(payment.getMoney());
            payment1.setPaymentStatus(State.randomStatus());
            paymentRepository.saveAndFlush(payment1);
            return payment1;
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
