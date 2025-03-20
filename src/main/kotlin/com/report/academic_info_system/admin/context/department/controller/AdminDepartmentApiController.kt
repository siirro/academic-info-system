package com.report.academic_info_system.admin.context.department.controller

import com.report.academic_info_system.admin.context.department.form.DepartmentForm
import com.report.academic_info_system.admin.context.department.service.AdminDepartmentService
import com.report.academic_info_system.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/department")
class AdminDepartmentApiController(
    private val adminDepartmentService: AdminDepartmentService
) {
    @PostMapping
    fun postDepartment(@RequestBody @Validated form: DepartmentForm): ResponseEntity<Any> {
        adminDepartmentService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putDepartment(@PathVariable id: Long, @RequestBody form: DepartmentForm): ResponseEntity<Any> {
        adminDepartmentService.update(id, form)

        return ApiResponse.successUpdate()
    }
}