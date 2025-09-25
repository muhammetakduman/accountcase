package com.mamidev.accountcase.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
open class Account(

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    open var id: UUID? = null,

    open var balance: BigDecimal? = BigDecimal.ZERO,

    open var creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY) // Cascade.ALL kaldırıldı
    @JoinColumn(name = "customer_id", nullable = false)
    open var customer: Customer? = null,

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    open var transactions: MutableSet<Transaction> = hashSetOf()
) {
    // JPA için parametresiz kurucu (Hibernate bunu ister)
    protected constructor(): this(null, BigDecimal.ZERO, LocalDateTime.now(), null, hashSetOf())

    // Yardımcı kurucu (opsiyonel)
    constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime)
            : this(null, balance, creationDate, customer, hashSetOf())

    override fun equals(other: Any?) =
        this === other || (other is Account && id != null && id == other.id)

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
