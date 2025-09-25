package com.mamidev.accountcase.service;

import com.mamidev.accountcase.dto.CustomerDto;
import com.mamidev.accountcase.dto.CustomerDtoConverter;
import com.mamidev.accountcase.exception.CustomerNotFoundException;
import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    @Transactional(readOnly = true) // EKLE
    protected Customer findCustomerById(UUID id){
        return customerRepository.findById(UUID.fromString(String.valueOf(id))).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found : " + id));
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(UUID customerId) {
        return converter.convertToCustomerDto(
                customerRepository.findById(customerId).orElseThrow(
                        () -> new CustomerNotFoundException("Customer not found: " + customerId)
                )
        );
    }

}
