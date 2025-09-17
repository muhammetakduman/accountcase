package com.mamidev.accountcase.repository;

import com.mamidev.accountcase.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
