package com.sangyoon.kopring.sample.service

import com.sangyoon.kopring.common.exception.BadRequestException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.sample.dto.SampleData
import org.springframework.stereotype.Service

@Service
class SampleService {
	fun getSample() {
	}

	fun getSampleData(data: String): SampleData = SampleData(value = data)

	fun checkException(isError: Boolean) {
		if (isError) {
			throw BadRequestException(ErrorStatus.NOT_FOUND_MEMBER_EXCEPTION.message)
		}
	}
}
