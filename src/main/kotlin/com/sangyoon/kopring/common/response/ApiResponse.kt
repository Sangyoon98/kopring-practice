package com.sangyoon.kopring.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "공통 API 응답")
data class ApiResponse<T>(
	@field:Schema(description = "HTTP 상태 코드", example = "200")
	val status: Int,
	@field:Schema(description = "요청 성공 여부", example = "true")
	val success: Boolean,
	@field:Schema(description = "응답 메시지", example = "샘플 조회 성공")
	val message: String,
	@field:Schema(description = "응답 데이터")
	val data: T? = null,
) {
	companion object {
		fun successOnly(status: SuccessStatus): ResponseEntity<ApiResponse<Nothing>> =
			ResponseEntity.status(status.httpStatus)
				.body(
					ApiResponse(
						status = status.statusCode,
						success = true,
						message = status.message,
					),
				)

		fun <T> success(status: SuccessStatus, data: T): ResponseEntity<ApiResponse<T>> =
			ResponseEntity.status(status.httpStatus)
				.body(
					ApiResponse(
						status = status.statusCode,
						success = true,
						message = status.message,
						data = data,
					),
				)

		fun fail(status: Int, message: String): ApiResponse<Nothing> =
			ApiResponse(
				status = status,
				success = false,
				message = message,
			)

		fun failOnly(status: ErrorStatus): ApiResponse<Nothing> =
			ApiResponse(
				status = status.statusCode,
				success = false,
				message = status.message,
			)

		fun fail(httpStatus: HttpStatus, message: String): ResponseEntity<ApiResponse<Nothing>> =
			ResponseEntity.status(httpStatus)
				.body(fail(httpStatus.value(), message))
	}
}

enum class ErrorStatus(
	val httpStatus: HttpStatus,
	val message: String,
) {
	VALIDATION_REQUEST_MISSING_EXCEPTION(
		httpStatus = HttpStatus.BAD_REQUEST,
		message = "요청 값이 입력되지 않았습니다.",
	),
	USER_UNAUTHORIZED(
		httpStatus = HttpStatus.UNAUTHORIZED,
		message = "인증되지 않은 사용자입니다.",
	),
	NOT_FOUND_MEMBER_EXCEPTION(
		httpStatus = HttpStatus.NOT_FOUND,
		message = "존재하지 않는 사용자 입니다.",
	),
	FAIL_UPLOAD_EXCEPTION(
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
		message = "파일 업로드 실패하였습니다.",
	),
	;

	val statusCode: Int
		get() = httpStatus.value()
}

enum class SuccessStatus(
	val httpStatus: HttpStatus,
	val message: String,
) {
	SEND_SAMPLE_SUCCESS(
		httpStatus = HttpStatus.OK,
		message = "샘플 조회 성공",
	),
	CREATE_SAMPLE_SUCCESS(
		httpStatus = HttpStatus.CREATED,
		message = "샘플 등록 성공",
	),
	;

	val statusCode: Int
		get() = httpStatus.value()
}
