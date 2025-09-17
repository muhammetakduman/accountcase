package com.mamidev.accountcase.repository;

import com.mamidev.accountcase.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
