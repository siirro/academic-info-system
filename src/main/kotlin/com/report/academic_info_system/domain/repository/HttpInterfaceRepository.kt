package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.HttpInterface
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface HttpInterfaceRepository : JpaRepository<HttpInterface, Long> {
    fun countAllByCreatedDateTimeBetween(start: LocalDateTime, end: LocalDateTime): Long
}