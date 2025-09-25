package com.mamidev.accountcase.dto;

import com.mamidev.accountcase.model.Customer;
import com.mamidev.accountcase.model.Account;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter accountConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter accountConverter) {
        this.accountConverter = accountConverter;
    }

    /**
     * Customer -> AccountCustomerDto (id, name, surname)
     */
    public AccountCustomerDto convertToAccountCustomer(Customer from) {
        if (from == null) {
            return new AccountCustomerDto(null, "", "");
        }
        return new AccountCustomerDto(
                from.getId() != null ? from.getId().toString() : null, // UUID -> String
                from.getName(),
                from.getSurname()
        );
    }

    /**
     * Customer -> CustomerDto (id, name, surname, accounts[])
     */
    public CustomerDto convertToCustomerDto(Customer from) {
        if (from == null) return null;

        Set<CustomerAccountDto> accounts =
                (from.getAccounts() == null) ? new LinkedHashSet<>() :
                        from.getAccounts()
                                .stream()
                                .map(accountConverter::convert) // Account -> CustomerAccountDto
                                .collect(Collectors.toCollection(LinkedHashSet::new));

        return new CustomerDto(
                from.getId() != null ? UUID.fromString(from.getId().toString()) : null, // UUID -> String
                from.getName(),
                from.getSurname(),
                new LinkedHashSet<>(accounts)
        );
    }
}
