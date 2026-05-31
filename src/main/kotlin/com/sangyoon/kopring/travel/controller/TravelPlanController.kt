package com.sangyoon.kopring.travel.controller

import com.sangyoon.kopring.travel.service.TravelPlanService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Travel Plan", description = "여행 계획 API")
@RequestMapping("/api/v1/travel-plans")
class TravelPlanController(
    private val travelPlanService: TravelPlanService,
)
