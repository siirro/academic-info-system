package com.report.academic_info_system.presentation.repository

import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.entity.Lecture
import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.repository.DepartmentRepository
import com.report.academic_info_system.domain.repository.LectureRepository
import com.report.academic_info_system.domain.repository.StudentRepository
import org.springframework.stereotype.Repository

@Repository
class PresentationRepository(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val lectureRepository: LectureRepository
) {
    fun getStudents(): List<Student> {
        return studentRepository.findAll()
    }

    fun getDepartments(): List<Department> {
        return departmentRepository.findAll()
    }

    fun getLectures(): List<Lecture> {
        return lectureRepository.findAllByIsActive(true)
    }
}