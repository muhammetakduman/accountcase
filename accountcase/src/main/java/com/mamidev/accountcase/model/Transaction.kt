package com.mamidev.accountcase.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "account_transaction")
open class Transaction(

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    open var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 32)
    open var transactionType: TransactionType = TransactionType.INITIAL,

    @Column(precision = 38, scale = 2, nullable = false)
    open var amount: BigDecimal = BigDecimal.ZERO,

    @Column(name = "transaction_date", nullable = false)
    open var transactionDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    open var account: Account? = null
) {
    // JPA için no-arg ctor
    protected constructor() : this(
        id = null,
        transactionType = TransactionType.INITIAL,
        amount = BigDecimal.ZERO,
        transactionDate = LocalDateTime.now(),
        account = null
    )

    // Kullanışlı parametreli ctor (serviste kullanırsın)
    constructor(amount: BigDecimal, account: Account) : this(
        id = null,
        transactionType = TransactionType.INITIAL,
        amount = amount,
        transactionDate = LocalDateTime.now(),
        account = account
    )

    override fun equals(other: Any?) =
        this === other || (other is Transaction && id != null && id == other.id)

    override fun hashCode(): Int = id?.hashCode() ?: 0
}

enum class TransactionType { INITIAL, TRANSFER }
