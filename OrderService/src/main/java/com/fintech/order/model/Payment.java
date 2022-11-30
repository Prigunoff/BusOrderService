package com.fintech.order.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintech.order.enums.State;
import com.fintech.order.utils.PaymentSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonSerialize(using = PaymentSerializer.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private State paymentStatus;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "money_deposit")
    private Long money;


}
