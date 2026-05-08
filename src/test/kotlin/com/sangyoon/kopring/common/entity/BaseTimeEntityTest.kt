package com.sangyoon.kopring.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import kotlin.test.Test
import kotlin.test.assertNotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

class BaseTimeEntityTest {
	@Test
	fun `base time entity has auditing annotations`() {
		val type = BaseTimeEntity::class.java
		val createdAt = type.getDeclaredField("createdAt")
		val updatedAt = type.getDeclaredField("updatedAt")

		assertNotNull(type.getAnnotation(MappedSuperclass::class.java))
		assertNotNull(type.getAnnotation(EntityListeners::class.java))
		assertNotNull(createdAt.getAnnotation(CreatedDate::class.java))
		assertNotNull(createdAt.getAnnotation(Column::class.java))
		assertNotNull(updatedAt.getAnnotation(LastModifiedDate::class.java))
	}
}
