package com.fintech.order.service;

import com.fintech.order.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer readByCustomerId(long id);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(long id);

    List<Customer> getAllCustomers();
}
