package com.fintech.ticket.repository;

import com.fintech.ticket.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {

}
