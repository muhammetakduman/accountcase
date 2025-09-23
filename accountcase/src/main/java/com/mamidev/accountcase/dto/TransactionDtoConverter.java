package com.mamidev.accountcase.dto;

import com.mamidev.accountcase.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {
    public TransactionDto convert(Transaction from){
        return new TransactionDto(from.getId().toString(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
