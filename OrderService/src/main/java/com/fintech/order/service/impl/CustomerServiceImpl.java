package com.fintech.order.service.impl;

import com.fintech.order.exceptions.NullEntityReferenceException;
import com.fintech.order.model.Customer;
import com.fintech.order.repository.CustomerRepository;
import com.fintech.order.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("customerServiceImpl")
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        if(customer != null){
            customerRepository.save(customer);
            return customer;
        }
        throw new NullEntityReferenceException("Customer cannot be 'null' ");
    }

    @Override
    public Customer readByCustomerId(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer with id: " + id + " not found.")
        );
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer != null){
            return customerRepository.save(customer);
        }
        throw new NullEntityReferenceException("Customer cannot be 'null'.");
    }

    @Override
    public void deleteCustomer(long id) {
    customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.getAllCustomersById();
        return allCustomers.isEmpty() ? new ArrayList<>() : allCustomers;
    }
}
