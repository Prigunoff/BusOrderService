package com.fintech.order.repository;

import com.fintech.order.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "select * from ticket order by id", nativeQuery = true)
    List<Customer> getAllCustomersById();
}
