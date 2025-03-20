package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.StudentDepartment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudentDepartmentRepository : JpaRepository<StudentDepartment, Long> {
    // @Query("SELECT sd FROM StudentDepartment sd WHERE sd.student.id = :stdId AND sd.department.id = :deptId")
    fun findByStdIdAndDeptId(stdId: Long, deptId: Long): Optional<StudentDepartment>
}