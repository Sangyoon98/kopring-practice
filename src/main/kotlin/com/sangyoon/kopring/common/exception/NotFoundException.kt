package com.sangyoon.kopring.common.exception

import org.springframework.http.HttpStatus

class NotFoundException(
	message: String? = null,
) : BaseException(HttpStatus.NOT_FOUND, message)
