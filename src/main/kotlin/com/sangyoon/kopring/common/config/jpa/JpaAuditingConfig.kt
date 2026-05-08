package com.sangyoon.kopring.common.config.jpa

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
@ConditionalOnProperty(
	name = ["spring.data.jpa.auditing.enabled"],
	havingValue = "true",
	matchIfMissing = true,
)
class JpaAuditingConfig
