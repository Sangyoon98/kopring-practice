package com.sangyoon.kopring.parent.controller

import com.sangyoon.kopring.common.response.ApiResponse
import com.sangyoon.kopring.common.response.SuccessStatus
import com.sangyoon.kopring.parent.dto.ParentProfileCreateRequest
import com.sangyoon.kopring.parent.dto.ParentProfileResponse
import com.sangyoon.kopring.parent.service.ParentProfileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Parent Profile", description = "부모님 프로필 API")
@RequestMapping("/api/v1/parent-profiles")
class ParentProfileController(
    private val parentProfileService: ParentProfileService,
) {
    @Operation(summary = "부모님 프로필 생성")
    @PostMapping
    fun createParentProfile(
        @RequestBody request: ParentProfileCreateRequest,
    ): ResponseEntity<ApiResponse<ParentProfileResponse>> {
        val response = parentProfileService.createParentProfile(request)

        return ApiResponse.success(SuccessStatus.CREATE_PARENT_PROFILE_SUCCESS, response)
    }

    @Operation(summary = "부모님 프로필 조회")
    @GetMapping("/{parentProfileId}")
    fun getParentProfile(
        @PathVariable parentProfileId: Long,
    ): ResponseEntity<ApiResponse<ParentProfileResponse>> {
        val response = parentProfileService.getParentProfile(parentProfileId)

        return ApiResponse.success(SuccessStatus.GET_PARENT_PROFILE_SUCCESS, response)
    }
}