package com.sangyoon.kopring.sample.controller

import com.sangyoon.kopring.common.response.ApiResponse
import com.sangyoon.kopring.common.response.SuccessStatus
import com.sangyoon.kopring.sample.service.SampleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sample")
class SampleController(
	private val sampleService: SampleService,
) {
	@GetMapping("/getSample")
	fun getSample(): ResponseEntity<ApiResponse<Nothing>> {
		sampleService.getSample()
		return ApiResponse.successOnly(SuccessStatus.SEND_SAMPLE_SUCCESS)
	}

	@GetMapping("/getSampleData")
	fun getSampleData(@RequestParam data: String): ResponseEntity<ApiResponse<String>> {
		val sampleData = sampleService.getSampleData(data)

		return ApiResponse.success(SuccessStatus.SEND_SAMPLE_SUCCESS, sampleData.value)
	}

	@GetMapping("/exception/{isError}")
	fun exception(@PathVariable isError: Boolean): ResponseEntity<ApiResponse<Nothing>> {
		sampleService.checkException(isError)

		return ApiResponse.successOnly(SuccessStatus.SEND_SAMPLE_SUCCESS)
	}
}
