package com.mamidev.accountcase.service;


import com.mamidev.accountcase.dto.AccountDto;
import com.mamidev.accountcase.dto.AccountDtoConverter;
import com.mamidev.accountcase.model.Account;
import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.model.Transaction;
import com.mamidev.accountcase.repository.AccountRepository;
import org.springframework.stereotype.Service;
import com.mamidev.accountcase.dto.CreateAccountRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          TransactionService transactionService, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = transactionService.initiateMoney(account,createAccountRequest.getInitialCredit());
            account.getTransactions().add(transaction);
        }
        return converter.convert(accountRepository.save(account));
    }
}
