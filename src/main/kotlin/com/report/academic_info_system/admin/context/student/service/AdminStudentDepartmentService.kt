package com.report.academic_info_system.admin.context.student.service

import com.report.academic_info_system.admin.context.student.form.StudentDepartmentForm
import com.report.academic_info_system.admin.data.TableDTO
import com.report.academic_info_system.admin.exception.AdminBadRequestException
import com.report.academic_info_system.admin.exception.AdminInternalServerErrorException
import com.report.academic_info_system.domain.entity.StudentDepartment
import com.report.academic_info_system.domain.repository.DepartmentRepository
import com.report.academic_info_system.domain.repository.StudentDepartmentRepository
import com.report.academic_info_system.domain.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminStudentDepartmentService(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val studentDepartmentRepository: StudentDepartmentRepository
) {
    @Transactional
    fun getStudentDepartmentTable(): TableDTO {
        val students = studentRepository.findAll()
        val columns = mutableListOf<String>(
            "id", "studentId", "studentName", "departmentId", "departmentName", "createDateTime", "updateDateTime"
        )
        val records = mutableListOf<MutableList<String>>()
        for (student in students) {
            student.stdDepts.forEach {
                val record = mutableListOf<String>()
                record.add(it.id.toString())
                record.add(it.student.id.toString())
                record.add(it.student.stdNm)
                record.add(it.department.id.toString())
                record.add(it.department.deptNm)
                record.add(it.createdDateTime.toString())
                record.add(it.updatedDateTime.toString())
                records.add(record)
            }

        }

        return TableDTO(name = "StudentDepartment", records = records, columns = columns)
    }

    fun getStudentList(): List<String> {
        val students = studentRepository.findAll()
        return students.map { "${it.id} (${it.stdNm}" }.toList()
    }

    fun getDepartmentList(): List<String> {
        val departments = departmentRepository.findAll()
        return departments.map { "${it.id} (${it.deptNm}" }.toList()
    }

    @Transactional
    fun save(form: StudentDepartmentForm) {
        val studentId = parseId(form.student)
        val departmentId = parseId(form.department)
        studentDepartmentRepository.findByStdIdAndDeptId(studentId, departmentId)
            .ifPresent { throw AdminBadRequestException("이미 매핑된 데이터입니다.") }
        val student = studentRepository.findById(studentId)
            .orElseThrow { throw AdminBadRequestException("ID ${studentId}에 해당하는 데이터가 없습니다.") }
        val department = departmentRepository.findById(departmentId)
            .orElseThrow { throw AdminBadRequestException("ID ${departmentId}에 해당하는 데이터가 없습니다.") }
        val studentDepartment = StudentDepartment(student, department, true)
        student.stdDepts.add(studentDepartment)
    }

    private fun parseId(line: String): Long {
        try {
            val endIndex = line.indexOf(" ") - 1
            val id = line.slice(0..endIndex).toLong()

            return id
        } catch (e: Exception) {
            throw AdminInternalServerErrorException("ID 추출 중 오류가 발생.")
        }
    }

    @Transactional
    fun delete(id: Long) {
        studentDepartmentRepository.deleteById(id)
    }
}