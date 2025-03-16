package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentLectureRepository : JpaRepository<Student, Long>