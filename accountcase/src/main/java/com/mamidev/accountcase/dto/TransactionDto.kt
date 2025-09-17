package com.mamidev.accountcase.dto

import com.mamidev.accountcase.model.TransactionType
import org.hibernate.validator.constraints.UUID
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
    val id: String?,
    val transactionType: TransactionType? = TransactionType.INITIAL,
    val amount: BigDecimal?,
    val transactionDate: LocalDateTime?
){

}
