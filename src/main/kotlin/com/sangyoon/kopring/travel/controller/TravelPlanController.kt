package com.sangyoon.kopring.travel.controller

import com.sangyoon.kopring.common.response.ApiResponse
import com.sangyoon.kopring.common.response.SuccessStatus
import com.sangyoon.kopring.travel.dto.TravelPlanCreateRequest
import com.sangyoon.kopring.travel.dto.TravelPlanResponse
import com.sangyoon.kopring.travel.service.TravelPlanService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Travel Plan", description = "여행 계획 API")
@RequestMapping("/api/v1/travel-plans")
class TravelPlanController(
    private val travelPlanService: TravelPlanService,
) {
    @Operation(summary = "여행 계획 생성")
    @PostMapping
    fun createTravelPlan(
        @Valid @RequestBody request: TravelPlanCreateRequest,
    ): ResponseEntity<ApiResponse<TravelPlanResponse>> {
        val response = travelPlanService.createTravelPlan(request)

        return ApiResponse.success(SuccessStatus.CREATE_TRAVEL_PLAN_SUCCESS, response)
    }

    @Operation(summary = "여행 계획 조회")
    @GetMapping("/{travelPlanId}")
    fun getTravelPlan(
        @PathVariable travelPlanId: Long,
    ): ResponseEntity<ApiResponse<TravelPlanResponse>> {
        val response = travelPlanService.getTravelPlan(travelPlanId)

        return ApiResponse.success(SuccessStatus.GET_TRAVEL_PLAN_SUCCESS, response)
    }
}
