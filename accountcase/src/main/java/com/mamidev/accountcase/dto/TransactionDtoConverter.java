package com.mamidev.accountcase.dto;

import com.mamidev.accountcase.model.Transaction;

public class TransactionDtoConverter {
    public TransactionDto convert(Transaction from){
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
