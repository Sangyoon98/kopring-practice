package com.sangyoon.kopring.sample.service

import com.sangyoon.kopring.common.exception.BadRequestException
import com.sangyoon.kopring.common.response.ErrorStatus
import org.springframework.http.HttpStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SampleServiceTest {
	private val sampleService = SampleService()

	@Test
	fun `get sample data returns requested data`() {
		val sampleData = sampleService.getSampleData("hello")

		assertEquals("hello", sampleData.value)
	}

	@Test
	fun `check exception throws bad request when error is true`() {
		val exception = assertFailsWith<BadRequestException> {
			sampleService.checkException(true)
		}

		assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
		assertEquals(ErrorStatus.NOT_FOUND_MEMBER_EXCEPTION.message, exception.responseMessage)
	}
}
