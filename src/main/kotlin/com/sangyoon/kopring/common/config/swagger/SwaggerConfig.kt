package com.sangyoon.kopring.common.config.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
	@Bean
	fun openAPI(): OpenAPI {
		val server = Server().url("/")

		return OpenAPI()
			.info(
				Info()
					.title("Sangyoon")
					.description("Sangyoon REST API Document")
					.version("1.0.0"),
			)
			.addServersItem(server)
	}
}
