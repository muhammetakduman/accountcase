package com.mamidev.accountcase.repository;

import com.mamidev.accountcase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository  extends JpaRepository<Customer, UUID> {
}
