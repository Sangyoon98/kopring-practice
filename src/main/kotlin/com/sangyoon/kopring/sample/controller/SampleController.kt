package com.sangyoon.kopring.sample.controller

import com.sangyoon.kopring.common.response.ApiResponse
import com.sangyoon.kopring.common.response.SuccessStatus
import com.sangyoon.kopring.sample.service.SampleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Sample", description = "Sample API 입니다.")
@RequestMapping("/api/v1/sample")
class SampleController(
	private val sampleService: SampleService,
) {
	@Operation(summary = "샘플 API", description = "샘플 데이터를 조회합니다.")
	@ApiResponses(
		value = [
			SwaggerApiResponse(responseCode = "200", description = "샘플 조회 성공"),
		],
	)
	@GetMapping("/getSample")
	fun getSample(): ResponseEntity<ApiResponse<Nothing>> {
		sampleService.getSample()
		return ApiResponse.successOnly(SuccessStatus.SEND_SAMPLE_SUCCESS)
	}

	@Operation(summary = "샘플 데이터 API", description = "샘플 데이터를 파라미터와 함께 조회합니다.")
	@ApiResponses(
		value = [
			SwaggerApiResponse(responseCode = "200", description = "샘플 데이터 조회 성공"),
			SwaggerApiResponse(responseCode = "400", description = "필수 요청 파라미터 누락"),
		],
	)
	@GetMapping("/getSampleData")
	fun getSampleData(
		@Parameter(description = "응답으로 반환할 샘플 데이터", example = "hello")
		@RequestParam data: String,
	): ResponseEntity<ApiResponse<String>> {
		val sampleData = sampleService.getSampleData(data)

		return ApiResponse.success(SuccessStatus.SEND_SAMPLE_SUCCESS, sampleData.value)
	}

	@Operation(summary = "샘플 에러 API", description = "샘플 에러 응답을 확인합니다.")
	@ApiResponses(
		value = [
			SwaggerApiResponse(responseCode = "200", description = "샘플 에러 성공"),
			SwaggerApiResponse(responseCode = "400", description = "샘플 에러 실패"),
		],
	)
	@GetMapping("/exception/{isError}")
	fun exception(
		@Parameter(description = "true이면 커스텀 예외를 발생시킵니다.", example = "true")
		@PathVariable isError: Boolean,
	): ResponseEntity<ApiResponse<Nothing>> {
		sampleService.checkException(isError)

		return ApiResponse.successOnly(SuccessStatus.SEND_SAMPLE_SUCCESS)
	}
}
