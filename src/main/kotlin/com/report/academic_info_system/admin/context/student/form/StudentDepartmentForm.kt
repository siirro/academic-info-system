package com.report.academic_info_system.admin.context.student.form

import jakarta.validation.constraints.NotBlank

data class StudentDepartmentForm(
    @field:NotBlank(message = "필수값입니다.")
    val student: String,
    @field:NotBlank(message = "필수값입니다.")
    val department: String
)