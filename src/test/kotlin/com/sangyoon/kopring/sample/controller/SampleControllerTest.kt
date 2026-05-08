package com.sangyoon.kopring.sample.controller

import com.sangyoon.kopring.common.advice.ControllerExceptionAdvice
import com.sangyoon.kopring.sample.service.SampleService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(SampleController::class)
@Import(SampleService::class, ControllerExceptionAdvice::class)
@TestPropertySource(properties = ["spring.data.jpa.auditing.enabled=false"])
class SampleControllerTest(
	@Autowired private val mockMvc: MockMvc,
) {
	@Test
	fun `GET sample returns success only response`() {
		mockMvc.perform(get("/api/v1/sample/getSample"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.status").value(200))
			.andExpect(jsonPath("$.message").value("샘플 조회 성공"))
	}

	@Test
	fun `GET sample data returns success response with data`() {
		mockMvc.perform(get("/api/v1/sample/getSampleData").param("data", "hello"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.status").value(200))
			.andExpect(jsonPath("$.message").value("샘플 조회 성공"))
			.andExpect(jsonPath("$.data").value("hello"))
	}

	@Test
	fun `GET sample data returns bad request when data parameter is missing`() {
		mockMvc.perform(get("/api/v1/sample/getSampleData"))
			.andExpect(status().isBadRequest)
			.andExpect(jsonPath("$.success").value(false))
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.message").value("요청 값이 입력되지 않았습니다."))
	}

	@Test
	fun `GET exception returns bad request when error is true`() {
		mockMvc.perform(get("/api/v1/sample/exception/true"))
			.andExpect(status().isBadRequest)
			.andExpect(jsonPath("$.success").value(false))
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.message").value("존재하지 않는 사용자 입니다."))
	}

	@Test
	fun `GET exception returns success when error is false`() {
		mockMvc.perform(get("/api/v1/sample/exception/false"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.status").value(200))
			.andExpect(jsonPath("$.message").value("샘플 조회 성공"))
	}
}
