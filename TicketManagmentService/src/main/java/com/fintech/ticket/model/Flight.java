package com.fintech.ticket.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
    private Integer id;
    @Column(name = "from_city", nullable = false)
    private String fromCity;
    @Column(name = "to_city", nullable = false)
    private String toCity;
    @Column(name = "train_start_time")
    private LocalDateTime trainStartTime;
    @Column(name = "available_tickets", nullable = false)
    private Integer availableTickets;
    @OneToMany(mappedBy = "flight_plan",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;

    public Integer getAvailableTickets() {
        return availableTickets = tickets.size();
    }
}
