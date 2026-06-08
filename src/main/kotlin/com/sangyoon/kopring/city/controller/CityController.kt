package com.sangyoon.kopring.city.controller

import com.sangyoon.kopring.city.service.CityService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "City", description = "도시 API")
@RestController
@RequestMapping("/api/v1/cities")
class CityController(
    private val cityService: CityService,
)
