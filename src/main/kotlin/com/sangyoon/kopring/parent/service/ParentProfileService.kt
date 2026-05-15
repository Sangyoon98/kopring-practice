package com.sangyoon.kopring.parent.service

import com.sangyoon.kopring.common.exception.NotFoundException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.parent.dto.ParentProfileCreateRequest
import com.sangyoon.kopring.parent.dto.ParentProfileResponse
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Service
class ParentProfileService {
    private val sequence = AtomicLong(1)
    private val parentProfiles = ConcurrentHashMap<Long, ParentProfileResponse>()

    fun createParentProfile(request: ParentProfileCreateRequest): ParentProfileResponse {
        val parentProfileId = sequence.getAndIncrement()

        val parentProfile = ParentProfileResponse(
            id = parentProfileId,
            nickname = request.nickname,
            ageRange = request.ageRange,
            walkingSpeed = request.walkingSpeed,
            preferStairs = request.preferStairs,
            restInterval = request.restInterval,
            preferredThemes = request.preferredThemes,
        )

        parentProfiles[parentProfileId] = parentProfile

        return parentProfile
    }

    fun getParentProfile(parentProfileId: Long): ParentProfileResponse =
        parentProfiles[parentProfileId]
            ?: throw NotFoundException(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.message)
}
