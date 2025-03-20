package com.report.academic_info_system.admin.context.student.controller

import com.report.academic_info_system.admin.context.student.form.StudentForm
import com.report.academic_info_system.admin.context.student.service.AdminStudentService
import com.report.academic_info_system.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/student")
class AdminStudentApiController(
    private val adminStudentService: AdminStudentService
) {
    @PostMapping
    fun postProject(@RequestBody @Validated form: StudentForm): ResponseEntity<Any> {
        adminStudentService.save(form)
        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putProject(@PathVariable id: Long, @RequestBody @Validated form: StudentForm): ResponseEntity<Any> {
        adminStudentService.update(id, form)
        return ApiResponse.successUpdate()
    }
}