package com.sangyoon.kopring.common.exception

import org.springframework.http.HttpStatus

class BadRequestException(
	message: String? = null,
) : BaseException(HttpStatus.BAD_REQUEST, message)
