package com.sangyoon.kopring.travel.dto

import com.sangyoon.kopring.travel.entity.TravelPlan
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Schema(description = "여행 계획 생성 요청")
data class TravelPlanCreateRequest(
    @field:NotNull(message = "부모님 프로필 ID는 필수입니다.")
    @field:Schema(description = "부모님 프로필 ID", example = "1")
    val parentProfileId: Long?,

    @field:NotBlank(message = "여행 계획 제목은 필수입니다.")
    @field:Schema(description = "여행 계획 제목", example = "엄마랑 경주 2박 3일")
    val title: String,

    @field:NotNull(message = "여행 시작일은 필수입니다.")
    @field:Schema(description = "여행 시작일", example = "2026-06-10")
    val startDate: LocalDate?,

    @field:NotNull(message = "여행 종료일은 필수입니다.")
    @field:Schema(description = "여행 종료일", example = "2026-06-12")
    val endDate: LocalDate?,

    @field:NotBlank(message = "출발지는 필수입니다.")
    @field:Schema(description = "출발지", example = "서울역")
    val departurePlace: String,

    @field:NotEmpty(message = "선호 테마는 1개 이상 선택해야 합니다.")
    @field:Schema(description = "이번 여행의 선호 테마", example = "[\"자연풍경\", \"전통시장\"]")
    val preferredThemes: List<String>,
)

@Schema(description = "여행 계획 응답")
data class TravelPlanResponse(
    @field:Schema(description = "여행 계획 ID", example = "1")
    val id: Long,
    @field:Schema(description = "부모님 프로필 ID", example = "1")
    val parentProfileId: Long,
    @field:Schema(description = "여행 계획 제목", example = "엄마랑 경주 2박 3일")
    val title: String,
    @field:Schema(description = "여행 시작일", example = "2026-06-10")
    val startDate: LocalDate,
    @field:Schema(description = "여행 종료일", example = "2026-06-12")
    val endDate: LocalDate,
    @field:Schema(description = "출발지", example = "서울역")
    val departurePlace: String,
    @field:Schema(description = "이번 여행의 선호 테마", example = "[\"자연풍경\", \"전통시장\"]")
    val preferredThemes: List<String>,
) {
    companion object {
        fun from(travelPlan: TravelPlan): TravelPlanResponse =
            TravelPlanResponse(
                id = travelPlan.id!!,
                parentProfileId = travelPlan.parentProfile.id!!,
                title = travelPlan.title,
                startDate = travelPlan.startDate,
                endDate = travelPlan.endDate,
                departurePlace = travelPlan.departurePlace,
                preferredThemes = travelPlan.preferredThemes,
            )
    }
}