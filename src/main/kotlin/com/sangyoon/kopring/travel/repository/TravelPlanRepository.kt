package com.sangyoon.kopring.travel.repository

import com.sangyoon.kopring.travel.entity.TravelPlan
import org.springframework.data.jpa.repository.JpaRepository

interface TravelPlanRepository : JpaRepository<TravelPlan, Long>
