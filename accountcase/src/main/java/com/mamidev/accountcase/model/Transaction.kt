package com.mamidev.accountcase.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class Transaction(

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")

    val id: String? = null,

    @Enumerated(EnumType.STRING)
    val transactionType: TransactionType = TransactionType.INITIAL,

    val amount: BigDecimal,

    val transactionDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY ,optional = false )
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account
) {
    constructor(
        amount: BigDecimal,
        account: Account
    ) : this(
        id = null,
        amount = amount,
        transactionDate = LocalDateTime.now(),
        transactionType = TransactionType.INITIAL,
        account = account
    )
}

enum class TransactionType {
    INITIAL,
    TRANSFER
}
