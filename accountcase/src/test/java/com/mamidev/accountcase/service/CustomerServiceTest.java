package com.mamidev.accountcase.service;

import com.mamidev.accountcase.dto.CustomerDto;
import com.mamidev.accountcase.dto.CustomerDtoConverter;
import com.mamidev.accountcase.exception.CustomerNotFoundException;
import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;


import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, converter);
    }

    @Test
    void testFindByCustomerId_whenCustomerIdExists() {
        UUID id = UUID.randomUUID();

        // Not: Entity constructor’ın senin projendeki imzasına göre uyarlayabilirsin
        Customer customer = new Customer(
                id,
                "name",
                "surname",
                Set.of()
        );

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        // Act
        Customer result = service.findCustomerById(id);

        // Assert
        assertEquals(customer, result);
        verify(customerRepository).findById(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        UUID id = UUID.randomUUID();
        Mockito.when(customerRepository.findById((id))).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,() -> service.findCustomerById(id));
    }

    @Test
    void testgetCustomerById_whenCustomerIdExists() {
        UUID id = UUID.randomUUID();

        // Not: Entity constructor’ın senin projendeki imzasına göre uyarlayabilirsin
        Customer customer = new Customer(
                id,
                "name",
                "surname",
                Set.of()
        );

        CustomerDto customerDto =  new CustomerDto(
                id,
                "name",
                "surname",
                Set.of()
        );

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        // Act
        CustomerDto result = service.getCustomerById(id);

        // Assert
        assertEquals(result, customerDto);
        verify(customerRepository).findById(id);
        verifyNoMoreInteractions(customerRepository);
    }
    @Test
    public void testGetByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        UUID id = UUID.randomUUID();
        Mockito.when(customerRepository.findById((id))).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,() -> service.getCustomerById(id));
        Mockito.verifyNoInteractions(converter);
    }
}