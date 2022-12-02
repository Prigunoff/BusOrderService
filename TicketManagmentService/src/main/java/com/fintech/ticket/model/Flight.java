package com.fintech.ticket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_city")
    private String fromCity;
    @Column(name = "to_city")
    private String toCity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY/MM/DD hh:mm:ss")
    @Column(name = "train_start_time")
    private LocalDateTime trainStartTime;
    @Column(name = "available_tickets", nullable = false)
    private Integer availableTickets;
    @JsonIgnore
    @OneToMany(mappedBy = "flight_plan", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;

}
