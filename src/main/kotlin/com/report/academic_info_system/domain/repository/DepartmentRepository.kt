package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, Long>