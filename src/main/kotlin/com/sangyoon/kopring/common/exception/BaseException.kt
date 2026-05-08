package com.sangyoon.kopring.common.exception

import org.springframework.http.HttpStatus

open class BaseException(
	val statusCode: HttpStatus,
	val responseMessage: String? = null,
) : RuntimeException(responseMessage) {
	val statusCodeValue: Int
		get() = statusCode.value()
}
