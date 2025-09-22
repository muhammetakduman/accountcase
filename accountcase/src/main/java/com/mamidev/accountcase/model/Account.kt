package com.mamidev.accountcase.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Account(

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")   // PG'de gerçek uuid sütunu
    val id: UUID? = null,

    val balance: BigDecimal? = BigDecimal.ZERO,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    // ManyToOne'da Cascade.ALL genelde önerilmez (özellikle delete cascade)
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL] )
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER , cascade = [CascadeType.ALL])
    val transactions: Set<Transaction> = HashSet()

) {
    constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) : this(
        id = null,
        customer = customer,
        balance = balance,
        creationDate = creationDate,
        transactions = HashSet()
    )

    // Sadece id'ye göre equals/hashCode genelde daha güvenli
    override fun equals(other: Any?) =
        this === other || (other is Account && id != null && id == other.id)

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
