package com.report.academic_info_system.admin.context.student.controller

import com.report.academic_info_system.admin.context.student.form.StudentDepartmentForm
import com.report.academic_info_system.admin.context.student.service.AdminStudentDepartmentService
import com.report.academic_info_system.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/student/department")
class AdminStudentDepartmentApiController(
    private val adminStudentDepartmentService: AdminStudentDepartmentService
) {
    @PostMapping
    fun postStudentDepartment(@RequestBody @Validated form: StudentDepartmentForm): ResponseEntity<Any> {
        adminStudentDepartmentService.save(form)
        return ApiResponse.successCreate()
    }

    @DeleteMapping("/{id}")
    fun deleteStudentDepartment(@PathVariable id: Long): ResponseEntity<Any> {
        adminStudentDepartmentService.delete(id)
        return ApiResponse.successDelete()
    }

}