package com.sangyoon.kopring.parent.entity

import com.sangyoon.kopring.common.entity.BaseTimeEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "parent_profiles")
class ParentProfile(
    @Column(nullable = false, length = 50)
    val nickname: String,

    @Column(nullable = false, length = 20)
    val ageRange: String,

    @Column(nullable = false, length = 20)
    val walkingSpeed: String,

    @Column(nullable = false)
    val preferStairs: Boolean,

    @Column(nullable = false, length = 50)
    val restInterval: String,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "parent_profile_preferred_themes",
        joinColumns = [JoinColumn(name = "parent_profile_id")],
    )
    @Column(name = "theme", nullable = false, length = 50)
    val preferredThemes: MutableList<String> = mutableListOf(),
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
