package com.sangyoon.kopring.parent.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "부모님 프로필 생성 요청")
data class ParentProfileCreateRequest(
    @field:Schema(description = "부모님 호칭", example = "염마")
    val nickname: String,
    @field:Schema(description = "연령대", example = "60대")
    val ageRange: String,
    @field:Schema(description = "걷기 속도", example = "느림")
    val walkingSpeed: String,
    @field:Schema(description = "계단 선호 여부", example = "false")
    val preferStairs: Boolean,
    @field:Schema(description = "휴식 간격", example = "30분마다")
    val restInterval: String,
    @field:Schema(description = "선호 테마", example = "[\"전통시장\", \"사찰/역사\"]")
    val preferredThemes: List<String>,
)

@Schema(description = "부모님 프로필 응답")
data class ParentProfileResponse(
    @field:Schema(description = "부모님 프로필 ID", example = "1")
    val id: Long,
    @field:Schema(description = "부모님 호칭", example = "엄마")
    val nickname: String,
    @field:Schema(description = "연령대", example = "60대")
    val ageRange: String,
    @field:Schema(description = "걷기 속도", example = "느림")
    val walkingSpeed: String,
    @field:Schema(description = "계단 선호 여부", example = "false")
    val preferStairs: Boolean,
    @field:Schema(description = "휴식 간격", example = "30분마다")
    val restInterval: String,
    @field:Schema(description = "선호 테마", example = "[\"전통시장\", \"사찰/역사\"]")
    val preferredThemes: List<String>,
)