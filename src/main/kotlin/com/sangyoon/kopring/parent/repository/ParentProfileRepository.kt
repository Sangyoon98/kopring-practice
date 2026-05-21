package com.sangyoon.kopring.parent.repository

import com.sangyoon.kopring.parent.entity.ParentProfile
import org.springframework.data.jpa.repository.JpaRepository

interface ParentProfileRepository : JpaRepository<ParentProfile, Long>
