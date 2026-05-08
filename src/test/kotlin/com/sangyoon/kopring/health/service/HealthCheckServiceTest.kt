package com.sangyoon.kopring.health.service

import kotlin.test.Test
import kotlin.test.assertEquals

class HealthCheckServiceTest {
	private val healthCheckService = HealthCheckService()

	@Test
	fun `health status is up`() {
		val healthStatus = healthCheckService.getHealth()

		assertEquals("UP", healthStatus.status)
	}
}
