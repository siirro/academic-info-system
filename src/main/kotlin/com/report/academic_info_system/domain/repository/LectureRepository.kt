package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository : JpaRepository<Lecture, Long> {
    fun findAllByIsActive(isActive: Boolean): List<Lecture>
}