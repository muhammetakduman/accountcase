package com.mamidev.accountcase.repository;

import com.mamidev.accountcase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,String> {
}
