package com.mamidev.accountcase.dto

import java.util.*

data class CustomerDto(
    val id: UUID? = null,
    val name: String,
    val surname: String,
    val accounts: MutableSet<CustomerAccountDto>
)
