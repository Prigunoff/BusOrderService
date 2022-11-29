package com.fintech.ticket.repository;

import com.fintech.ticket.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select id,available_tickets,from_city,to_city,train_start_time " +
            "from flight order by id", nativeQuery = true)
    List<Flight>getAllById();
}
