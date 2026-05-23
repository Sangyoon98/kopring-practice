package com.sangyoon.kopring.parent.service

import com.sangyoon.kopring.common.exception.NotFoundException
import com.sangyoon.kopring.common.response.ErrorStatus
import com.sangyoon.kopring.parent.dto.ParentProfileCreateRequest
import com.sangyoon.kopring.parent.dto.ParentProfileResponse
import com.sangyoon.kopring.parent.entity.ParentProfile
import com.sangyoon.kopring.parent.repository.ParentProfileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ParentProfileService(
    private val parentProfileRepository: ParentProfileRepository,
) {
    @Transactional
    fun createParentProfile(request: ParentProfileCreateRequest): ParentProfileResponse {
        val parentProfile = ParentProfile(
            nickname = request.nickname,
            ageRange = request.ageRange,
            walkingSpeed = request.walkingSpeed,
            preferStairs = request.preferStairs,
            restInterval = request.restInterval,
            preferredThemes = request.preferredThemes.toMutableList(),
        )

        val savedParentProfile = parentProfileRepository.save(parentProfile)

        return ParentProfileResponse.from(savedParentProfile)
    }

    fun getParentProfile(parentProfileId: Long): ParentProfileResponse {
        val parentProfile = parentProfileRepository.findByIdOrNull(parentProfileId)
            ?: throw NotFoundException(ErrorStatus.NOT_FOUND_PARENT_PROFILE_EXCEPTION.message)

        return ParentProfileResponse.from(parentProfile)
    }
}
