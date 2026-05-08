package com.sangyoon.kopring.sample.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "샘플 데이터")
data class SampleData(
	@field:Schema(description = "샘플 값", example = "hello")
	val value: String,
)
