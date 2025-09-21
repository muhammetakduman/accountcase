package com.mamidev.accountcase;

import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class Accountcasestarter implements CommandLineRunner {

	private  final CustomerRepository customerRepository;

    public Accountcasestarter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Accountcasestarter.class, args);
	}


	@Override
	public void run(String... args) {
		Customer c = customerRepository.save(new Customer("Muhammet","Akduman"));
		System.out.println("Seed Customer ID: " + c.getId());  // <-- Bunu kopyala
	}

}
