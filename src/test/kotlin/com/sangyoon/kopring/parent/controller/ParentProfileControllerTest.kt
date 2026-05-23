package com.sangyoon.kopring.parent.controller

import com.sangyoon.kopring.common.advice.ControllerExceptionAdvice
import com.sangyoon.kopring.common.exception.NotFoundException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.parent.dto.ParentProfileCreateRequest
import com.sangyoon.kopring.parent.dto.ParentProfileResponse
import com.sangyoon.kopring.parent.service.ParentProfileService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ParentProfileController::class)
@Import(ControllerExceptionAdvice::class)
@TestPropertySource(properties = ["spring.data.jpa.auditing.enabled=false"])
class ParentProfileControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {
    @MockitoBean
    private lateinit var parentProfileService: ParentProfileService

    @Test
    fun `부모님 프로필 생성 요청이 성공하면 201 응답을 반환한다`() {
        val request = ParentProfileCreateRequest(
            nickname = "엄마",
            ageRange = "60대",
            walkingSpeed = "느림",
            preferStairs = false,
            restInterval = "30분마다",
            preferredThemes = listOf("전통시장", "사찰/역사"),
        )
        val response = ParentProfileResponse(
            id = 1L,
            nickname = "엄마",
            ageRange = "60대",
            walkingSpeed = "느림",
            preferStairs = false,
            restInterval = "30분마다",
            preferredThemes = listOf("전통시장", "사찰/역사"),
        )
        val requestBody = """
			{
			  "nickname": "엄마",
			  "ageRange": "60대",
			  "walkingSpeed": "느림",
			  "preferStairs": false,
			  "restInterval": "30분마다",
			  "preferredThemes": ["전통시장", "사찰/역사"]
			}
		""".trimIndent()

        `when`(parentProfileService.createParentProfile(request)).thenReturn(response)

        mockMvc.perform(
            post("/api/v1/parent-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.status").value(201))
            .andExpect(jsonPath("$.message").value("부모님 프로필 생성 성공"))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.nickname").value("엄마"))
            .andExpect(jsonPath("$.data.ageRange").value("60대"))
            .andExpect(jsonPath("$.data.walkingSpeed").value("느림"))
            .andExpect(jsonPath("$.data.preferStairs").value(false))
            .andExpect(jsonPath("$.data.restInterval").value("30분마다"))
            .andExpect(jsonPath("$.data.preferredThemes[0]").value("전통시장"))
            .andExpect(jsonPath("$.data.preferredThemes[1]").value("사찰/역사"))
    }

    @Test
    fun `부모님 호칭이 비어 있으면 400 응답을 반환한다`() {
        val requestBody = """
			{
			  "nickname": "",
			  "ageRange": "60대",
			  "walkingSpeed": "느림",
			  "preferStairs": false,
			  "restInterval": "30분마다",
			  "preferredThemes": ["전통시장"]
			}
		""".trimIndent()

        mockMvc.perform(
            post("/api/v1/parent-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody),
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.message").value("부모님 호칭은 필수입니다. (nickname)"))
    }

    @Test
    fun `선호 테마가 비어 있으면 400 응답을 반환한다`() {
        val requestBody = """
			{
			  "nickname": "엄마",
			  "ageRange": "60대",
			  "walkingSpeed": "느림",
			  "preferStairs": false,
			  "restInterval": "30분마다",
			  "preferredThemes": []
			}
		""".trimIndent()

        mockMvc.perform(
            post("/api/v1/parent-profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody),
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.message").value("선호 테마는 1개 이상 선택해야 합니다. (preferredThemes)"))
    }

    @Test
    fun `부모님 프로필 조회 요청이 성공하면 200 응답을 반환한다`() {
        val response = ParentProfileResponse(
            id = 1L,
            nickname = "아빠",
            ageRange = "70대",
            walkingSpeed = "보통",
            preferStairs = true,
            restInterval = "1시간마다",
            preferredThemes = listOf("자연풍경"),
        )

        `when`(parentProfileService.getParentProfile(1L)).thenReturn(response)

        mockMvc.perform(get("/api/v1/parent-profiles/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.status").value(200))
            .andExpect(jsonPath("$.message").value("부모님 프로필 조회 성공"))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.nickname").value("아빠"))
            .andExpect(jsonPath("$.data.ageRange").value("70대"))
            .andExpect(jsonPath("$.data.walkingSpeed").value("보통"))
            .andExpect(jsonPath("$.data.preferStairs").value(true))
            .andExpect(jsonPath("$.data.restInterval").value("1시간마다"))
            .andExpect(jsonPath("$.data.preferredThemes[0]").value("자연풍경"))
    }

    @Test
    fun `존재하지 않는 부모님 프로필을 조회하면 404 응답을 반환한다`() {
        `when`(parentProfileService.getParentProfile(999L))
            .thenThrow(NotFoundException(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.message))

        mockMvc.perform(get("/api/v1/parent-profiles/999"))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").value("부모님 프로필을 찾을 수 없습니다."))
    }
}
