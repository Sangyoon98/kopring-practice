package com.sangyoon.kopring.parent.service

import com.sangyoon.kopring.common.exception.NotFoundException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.parent.dto.ParentProfileCreateRequest
import com.sangyoon.kopring.parent.repository.ParentProfileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@SpringBootTest(
    properties = [
        "spring.datasource.url=jdbc:h2:mem:parent-profile-service-test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.data.jpa.auditing.enabled=false",
    ],
)
@Transactional
class ParentProfileServiceTest(
    @Autowired private val parentProfileService: ParentProfileService,
    @Autowired private val parentProfileRepository: ParentProfileRepository,
) {
    @Test
    fun `부모님 프로필을 생성하면 응답과 DB 저장 데이터가 일치한다`() {
        val request = ParentProfileCreateRequest(
            nickname = "엄마",
            ageRange = "60대",
            walkingSpeed = "느림",
            preferStairs = false,
            restInterval = "30분마다",
            preferredThemes = listOf("전통시장", "사찰/역사"),
        )

        val response = parentProfileService.createParentProfile(request)

        assertNotNull(response.id)
        assertEquals("엄마", response.nickname)
        assertEquals("60대", response.ageRange)
        assertEquals("느림", response.walkingSpeed)
        assertFalse(response.preferStairs)
        assertEquals("30분마다", response.restInterval)
        assertEquals(listOf("전통시장", "사찰/역사"), response.preferredThemes)

        val savedParentProfile = parentProfileRepository.findById(response.id).orElseThrow()

        assertEquals("엄마", savedParentProfile.nickname)
        assertEquals("60대", savedParentProfile.ageRange)
        assertEquals("느림", savedParentProfile.walkingSpeed)
        assertFalse(savedParentProfile.preferStairs)
        assertEquals("30분마다", savedParentProfile.restInterval)
        assertEquals(listOf("전통시장", "사찰/역사"), savedParentProfile.preferredThemes)
    }

    @Test
    fun `부모님 프로필을 조회하면 저장된 프로필 응답을 반환한다`() {
        val request = ParentProfileCreateRequest(
            nickname = "아빠",
            ageRange = "70대",
            walkingSpeed = "보통",
            preferStairs = true,
            restInterval = "1시간마다",
            preferredThemes = listOf("자연풍경"),
        )
        val createdResponse = parentProfileService.createParentProfile(request)

        val response = parentProfileService.getParentProfile(createdResponse.id)

        assertEquals(createdResponse.id, response.id)
        assertEquals("아빠", response.nickname)
        assertEquals("70대", response.ageRange)
        assertEquals("보통", response.walkingSpeed)
        assertEquals(true, response.preferStairs)
        assertEquals("1시간마다", response.restInterval)
        assertEquals(listOf("자연풍경"), response.preferredThemes)
    }

    @Test
    fun `존재하지 않는 부모님 프로필을 조회하면 NotFoundException이 발생한다`() {
        val exception = assertFailsWith<NotFoundException> {
            parentProfileService.getParentProfile(999L)
        }

        assertEquals(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.httpStatus, exception.statusCode)
        assertEquals(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.message, exception.responseMessage)
    }
}
