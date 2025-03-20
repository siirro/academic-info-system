package com.report.academic_info_system.admin.context.department.form

import com.report.academic_info_system.domain.entity.Department
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class DepartmentForm(
    @field:NotBlank(message = "필수값입니다.")
    val deptNm: String,
    val deptLoca: String?,
    @field:Positive(message = "0보다 커야합니다.")
    val deptCapa: Int?
) {
    fun toEntity(): Department {
        return Department(
            deptNm = this.deptNm,
            deptLoca = this.deptLoca,
            deptCapa = this.deptCapa
        )
    }

    fun toEntity(id: Long): Department {
        val department = this.toEntity()
        department.id = id

        return department
    }
}