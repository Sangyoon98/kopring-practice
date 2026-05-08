package com.sangyoon.kopring.common.exception

import org.springframework.http.HttpStatus

class UnAuthorizedException(
	message: String? = null,
) : BaseException(HttpStatus.UNAUTHORIZED, message)
