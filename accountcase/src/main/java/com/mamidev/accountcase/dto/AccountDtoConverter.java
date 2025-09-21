package com.mamidev.accountcase.dto;

import com.mamidev.accountcase.model.Account;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId() != null ? UUID.fromString(from.getId().toString()) : null,   // <-- kritik nokta
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                (from.getTransactions() == null ?
                        java.util.Set.of() :
                        from.getTransactions()
                                .stream()
                                .map(transactionDtoConverter::convert)
                                .collect(Collectors.toSet()))
        );
    }

}
