package com.report.academic_info_system.admin.context.department.service

import com.report.academic_info_system.admin.context.department.form.DepartmentForm
import com.report.academic_info_system.admin.data.TableDTO
import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.repository.DepartmentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminDepartmentService(
    private val departmentRepository: DepartmentRepository
) {
    fun getDepartmentTable(): TableDTO {
        val classInfo = Department::class
        val entities = departmentRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: DepartmentForm) {
        val department = form.toEntity()
        departmentRepository.save(department)
    }

    @Transactional
    fun update(id: Long, form: DepartmentForm) {
        val department = form.toEntity(id)
        departmentRepository.save(department)
    }
}