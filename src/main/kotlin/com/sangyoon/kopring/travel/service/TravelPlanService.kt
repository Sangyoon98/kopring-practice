package com.sangyoon.kopring.travel.service

import com.sangyoon.kopring.common.exception.NotFoundException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.parent.repository.ParentProfileRepository
import com.sangyoon.kopring.travel.dto.TravelPlanCreateRequest
import com.sangyoon.kopring.travel.dto.TravelPlanResponse
import com.sangyoon.kopring.travel.entity.TravelPlan
import com.sangyoon.kopring.travel.repository.TravelPlanRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TravelPlanService(
    private val parentProfileRepository: ParentProfileRepository,
    private val travelPlanRepository: TravelPlanRepository,
) {
    @Transactional
    fun createTravelPlan(request: TravelPlanCreateRequest): TravelPlanResponse {
        val parentProfile =
            parentProfileRepository.findByIdOrNull(request.parentProfileId!!)
                ?: throw NotFoundException(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.message)

        val travelPlan = TravelPlan(
            parentProfile = parentProfile,
            title = request.title,
            startDate = request.startDate!!,
            endDate = request.endDate!!,
            departurePlace = request.departurePlace,
            preferredThemes = request.preferredThemes.toMutableList(),
        )

        val savedTravelPlan = travelPlanRepository.save(travelPlan)

        return TravelPlanResponse.from(savedTravelPlan)
    }

    fun getTravelPlan(travelPlanId: Long): TravelPlanResponse {
        val travelPlan = travelPlanRepository.findByIdOrNull(travelPlanId)
            ?: throw NotFoundException(ErrorStatus.NOT_FOUND_TRAVEL_PLAN_EXCEPTION.message)

        return TravelPlanResponse.from(travelPlan)
    }
}
