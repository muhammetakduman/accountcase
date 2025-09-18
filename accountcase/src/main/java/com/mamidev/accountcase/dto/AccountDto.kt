package com.mamidev.accountcase.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class AccountDto (
    val id: UUID? = null,

    val balance: BigDecimal? = BigDecimal.ZERO,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    val  customer: AccountCustomerDto?,

    val transactions: Set<TransactionDto>?


)