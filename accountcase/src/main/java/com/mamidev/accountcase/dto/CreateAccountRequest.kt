package com.mamidev.accountcase.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateAccountRequest(
    @field:NotBlank(message = "Bu alan boş olamaz")
    val customerId: String,

    @field:Min(0,  message = "0 dan büyük veya eşit bir değer girmelisiniz.")
    val initialCredit: BigDecimal
)
