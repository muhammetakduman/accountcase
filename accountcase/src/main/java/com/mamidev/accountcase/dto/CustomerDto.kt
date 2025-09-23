package com.mamidev.accountcase.dto

data class CustomerDto(
    val id: String? = null,
    val name: String,
    val surname: String,
    val accounts: MutableSet<CustomerAccountDto>
)