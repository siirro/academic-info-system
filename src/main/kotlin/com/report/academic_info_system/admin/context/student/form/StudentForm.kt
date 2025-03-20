package com.report.academic_info_system.admin.context.student.form

import com.report.academic_info_system.domain.entity.Student
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class StudentForm(
    val password: String?,
    @field:NotBlank(message = "필수값입니다.")
    val stdNm: String,
    val stdResideNo: String,
    val stdPhone: String,
    @field:Min(value = 1, message = "최소값은 1입니다.")
    @field:Max(value = 12, message = "최대값은 4입니다.")
    val stdYear: Int,
    val stdStatus: String,
    val stdEntryYmd: String
    // val stdDepts: List<ProjectDetailForm>?
) {
    fun toEntity(): Student {
        return Student(
            password = this.password,
            stdNm = this.stdNm,
            stdResideNo = this.stdResideNo,
            stdPhone = this.stdPhone,
            stdYear = this.stdYear,
            stdStatus = this.stdStatus,
            stdEntryYmd = this.stdEntryYmd
        )
    }
}