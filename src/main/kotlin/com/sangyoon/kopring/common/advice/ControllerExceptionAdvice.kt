package com.sangyoon.kopring.common.advice

import com.sangyoon.kopring.common.exception.BaseException
import com.sangyoon.kopring.common.response.ApiResponse
import com.sangyoon.kopring.common.response.ErrorStatus
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionAdvice {
	@ExceptionHandler(BaseException::class)
	fun handleGlobalException(exception: BaseException): ResponseEntity<ApiResponse<Nothing>> =
		ApiResponse.fail(
			exception.statusCode,
			exception.responseMessage ?: exception.statusCode.reasonPhrase,
		)

	@ExceptionHandler(MissingServletRequestParameterException::class)
	fun handleMissingParameter(exception: MissingServletRequestParameterException): ResponseEntity<ApiResponse<Nothing>> =
		ResponseEntity.status(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION.httpStatus)
			.body(ApiResponse.failOnly(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION))

	@ExceptionHandler(IllegalArgumentException::class)
	fun handleIllegalArgument(exception: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> =
		ApiResponse.fail(
			HttpStatus.BAD_REQUEST,
			exception.message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
		)

	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
		val fieldError: FieldError? = exception.fieldError
		val message = fieldError
			?.let { "${it.defaultMessage} (${it.field})" }
			?: HttpStatus.BAD_REQUEST.reasonPhrase

		return ApiResponse.fail(HttpStatus.BAD_REQUEST, message)
	}
}
