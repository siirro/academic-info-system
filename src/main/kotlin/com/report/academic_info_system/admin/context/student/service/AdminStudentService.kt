package com.report.academic_info_system.admin.context.student.service

import com.report.academic_info_system.admin.context.student.form.StudentForm
import com.report.academic_info_system.admin.data.TableDTO
import com.report.academic_info_system.admin.exception.AdminBadRequestException
import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.entity.StudentDepartment
import com.report.academic_info_system.domain.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminStudentService(
    private val studentRepository: StudentRepository
) {
    fun getStudentTable(): TableDTO {
        val classInfo = Student::class
        val entities = studentRepository.findAll()

        return TableDTO.from(classInfo, entities, "stdLects")
    }

    fun getStudentDepartmentTable(id: Long?): TableDTO {
        val classInfo = StudentDepartment::class
        val entities = if (id != null) studentRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 학생을 찾을 수 없습니다.") }
            .stdDepts else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: StudentForm) {
        val student = form.toEntity()
        studentRepository.save(student)
    }

    @Transactional
    fun update(id: Long, form: StudentForm) {
        val student = studentRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터가 없습니다.") }
        student.update(
            password = form.password,
            stdNm = form.stdNm,
            stdResideNo = form.stdResideNo,
            stdPhone = form.stdPhone,
            stdYear = form.stdYear,
            stdStatus = form.stdStatus,
            stdEntryYmd = form.stdEntryYmd
        )
    }
}