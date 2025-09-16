package com.mamidev.accountcase.model

import jakarta.persistence.*
import java.util.UUID
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class Account(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    val balance: BigDecimal? = BigDecimal.ZERO,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", nullable = false)

    val  customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    val trasaction: Set<Transaction>?

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (creationDate != other.creationDate) return false
        if (customer != other.customer) return false
        if (trasaction != other.trasaction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + creationDate.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        return result
    }
}
