package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.entity.StudentDepartment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface StudentDepartmentRepository : JpaRepository<Student, Long> {
}