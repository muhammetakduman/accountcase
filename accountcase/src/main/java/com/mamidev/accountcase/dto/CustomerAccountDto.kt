package com.mamidev.accountcase.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CustomerAccountDto(
    val id: UUID,
    var balance: BigDecimal? = BigDecimal.ZERO,
    val transactions: MutableSet<TransactionDto>?,
    val creationDate: LocalDateTime
)
