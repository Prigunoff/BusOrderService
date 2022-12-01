package com.fintech.order.controller;

import com.fintech.order.model.Payment;
import com.fintech.order.service.PaymentService;
import com.fintech.order.utils.PaymentStatusDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "api/payment")
public class PaymentRestController {
    private PaymentService paymentService;
    @PostMapping("/create")
    public void createPayment(@RequestBody Payment payment){
        paymentService.createPayment(payment);
    }
    @PostMapping("/process")
    public ResponseEntity<Payment> depositMoneyForPayment(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.updatePayment(payment),HttpStatus.OK);
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<PaymentStatusDto> getStatusOfPayment(@PathVariable("id") Long paymentId){
        Payment payment = paymentService.readByPaymentId(paymentId);
        PaymentStatusDto paymentStatus = paymentService.mapToDto(payment);
        log.info("PaymentController:GET:getStatusOfPayment(): Getting status for payment id: " + payment.getId()
                + ", Status: " + paymentStatus.getStatus());
        return new ResponseEntity<>(paymentStatus,HttpStatus.OK);
    }
}
