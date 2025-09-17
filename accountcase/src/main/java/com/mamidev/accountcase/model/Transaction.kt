package com.mamidev.accountcase.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Transaction(

    @Id
    @GeneratedValue
    val id: String? = null,

    @Enumerated(EnumType.STRING)
    val transactionType: TransactionType = TransactionType.INITIAL,

    val amount: BigDecimal,

    val transactionDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (id != other.id) return false
        if (transactionType != other.transactionType) return false
        if (amount != other.amount) return false
        if (transactionDate != other.transactionDate) return false
        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + transactionType.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + transactionDate.hashCode()
        return result
    }
}

enum class TransactionType {
    INITIAL,
    TRANSFER
}

