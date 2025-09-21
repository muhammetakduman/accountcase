package com.mamidev.accountcase.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator


@Entity
class Customer(

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    val id: String? = null,

    val name: String? = null,

    val surname: String? = null,

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val accounts: Set<Account> = emptySet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (accounts != other.accounts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        return result
    }
}
