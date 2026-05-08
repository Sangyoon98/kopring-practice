package com.sangyoon.kopring.health.controller

import com.sangyoon.kopring.health.service.HealthCheckService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HealthCheckController::class)
@Import(HealthCheckService::class)
@TestPropertySource(properties = ["spring.data.jpa.auditing.enabled=false"])
class HealthCheckControllerTest(
	@Autowired private val mockMvc: MockMvc,
) {
	@Test
	fun `GET health returns up`() {
		mockMvc.perform(get("/health"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.status").value("UP"))
	}
}
