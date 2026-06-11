package com.sangyoon.kopring.city.entity

import com.sangyoon.kopring.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cities")
class City(
    @Column(nullable = false, length = 50)
    val name: String,

    @Column(nullable = false, length = 50)
    val region: String,

    @Column(nullable = false, length = 500)
    val description: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
