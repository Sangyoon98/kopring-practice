package com.sangyoon.kopring.health.service

import com.sangyoon.kopring.health.dto.HealthCheckResponse
import org.springframework.stereotype.Service

@Service
class HealthCheckService {
	fun getHealth(): HealthCheckResponse = HealthCheckResponse(status = "UP")
}
