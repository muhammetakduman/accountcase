package com.mamidev.accountcase.service;

import com.mamidev.accountcase.dto.CustomerDto;
import com.mamidev.accountcase.dto.CustomerDtoConverter;
import com.mamidev.accountcase.exception.CustomerNotFoundException;
import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;


    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    //post işlemi yapıldığı için dto gerek duyulmadı.
    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found : "+id));
    };


    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    };
}
