package com.sangyoon.kopring.health.controller

import com.sangyoon.kopring.health.dto.HealthCheckResponse
import com.sangyoon.kopring.health.service.HealthCheckService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController(
	private val healthCheckService: HealthCheckService,
) {
	@GetMapping("/health")
	fun getHealth(): HealthCheckResponse = healthCheckService.getHealth()
}
