package com.report.academic_info_system.presentation.repository

import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.repository.DepartmentRepository
import com.report.academic_info_system.domain.repository.StudentRepository
import org.springframework.stereotype.Repository

@Repository
class PresentationRepository(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository
) {
    fun getStudents(): List<Student> {
        return studentRepository.findAll()
    }

    fun getDepartments(): List<Department> {
        return departmentRepository.findAll()
    }
}