package com.fintech.ticket.repository;

import com.fintech.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query(value = "select * from ticket order by id", nativeQuery = true)
    List<Ticket> getAllById();

}
