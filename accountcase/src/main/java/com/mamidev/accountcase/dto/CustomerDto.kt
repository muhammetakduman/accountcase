package com.mamidev.accountcase.dto

import org.hibernate.validator.constraints.UUID

data class CustomerDto(
    val id:UUID? = null,
    val name: String,
    val surname: String,
    val accounts: Set<CustomerAccountDto>
)