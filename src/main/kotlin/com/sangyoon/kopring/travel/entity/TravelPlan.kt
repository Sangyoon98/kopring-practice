package com.sangyoon.kopring.travel.entity

import com.sangyoon.kopring.common.entity.BaseTimeEntity
import com.sangyoon.kopring.parent.entity.ParentProfile
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "travel_plans")
class TravelPlan(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_profile_id", nullable = false)
    val parentProfile: ParentProfile,

    @Column(nullable = false, length = 100)
    val title: String,

    @Column(nullable = false)
    val startDate: LocalDate,

    @Column(nullable = false)
    val endDate: LocalDate,

    @Column(nullable = false, length = 100)
    val departurePlace: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    val status: TravelPlanStatus = TravelPlanStatus.READY_TO_SELECT_CITY,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "travel_plan_preferred_themes",
        joinColumns = [JoinColumn(name = "travel_plan_id")],
    )
    @Column(name = "theme", nullable = false, length = 50)
    val preferredThemes: MutableList<String> = mutableListOf(),
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
