package com.mamidev.accountcase.service;

import com.mamidev.accountcase.exception.CustomerNotFoundException;
import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found : "+id));
    }
}
