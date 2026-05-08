package com.sangyoon.kopring.common.exception

import org.springframework.http.HttpStatus

class InternalServerException(
	message: String? = null,
) : BaseException(HttpStatus.INTERNAL_SERVER_ERROR, message)
