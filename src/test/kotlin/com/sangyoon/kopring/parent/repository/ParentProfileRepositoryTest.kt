package com.sangyoon.kopring.parent.repository

import com.sangyoon.kopring.common.config.jpa.JpaAuditingConfig
import com.sangyoon.kopring.parent.entity.ParentProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DataJpaTest
@Import(JpaAuditingConfig::class)
@TestPropertySource(properties = ["spring.data.jpa.auditing.enabled=true"])
class ParentProfileRepositoryTest(
    @Autowired private val parentProfileRepository: ParentProfileRepository,
) {
    @Test
    fun `부모님 프로필을 저장하고 조회할 수 있다`() {
        val parentProfile = ParentProfile(
            nickname = "엄마",
            ageRange = "60대",
            walkingSpeed = "느림",
            preferStairs = false,
            restInterval = "30분마다",
            preferredThemes = mutableListOf("전통시장", "사찰/역사"),
        )

        val savedParentProfile = parentProfileRepository.save(parentProfile)

        assertNotNull(savedParentProfile.id)

        val foundParentProfile = parentProfileRepository.findById(savedParentProfile.id!!)

        assertTrue(foundParentProfile.isPresent)
        assertEquals("엄마", foundParentProfile.get().nickname)
        assertEquals("60대", foundParentProfile.get().ageRange)
        assertEquals("느림", foundParentProfile.get().walkingSpeed)
        assertFalse(foundParentProfile.get().preferStairs)
        assertEquals("30분마다", foundParentProfile.get().restInterval)
        assertEquals(listOf("전통시장", "사찰/역사"), foundParentProfile.get().preferredThemes)
    }

    @Test
    fun `존재하지 않는 부모님 프로필을 조회하면 empty를 반환한다`() {
        val foundParentProfile = parentProfileRepository.findById(999L)

        assertTrue(foundParentProfile.isEmpty)
    }

    @Test
    fun `부모님 프로필을 저장하면 생성일과 수정일이 자동으로 기록된다`() {
        val parentProfile = ParentProfile(
            nickname = "엄마",
            ageRange = "60대",
            walkingSpeed = "느림",
            preferStairs = false,
            restInterval = "30분마다",
            preferredThemes = mutableListOf("전통시장", "사찰/역사"),
        )

        val savedParentProfile = parentProfileRepository.saveAndFlush(parentProfile)

        assertNotNull(savedParentProfile.createdAt)
        assertNotNull(savedParentProfile.updatedAt)
    }
}
