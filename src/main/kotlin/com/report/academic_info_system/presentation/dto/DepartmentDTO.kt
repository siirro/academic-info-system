package com.report.academic_info_system.presentation.dto

import com.report.academic_info_system.domain.entity.Department

class DepartmentDTO(
    val deptNm: String,
    val deptLoca: String?,
    val deptCapa: Int?
) {
    constructor(department: Department) : this(
        deptNm = department.deptNm,
        deptLoca = department.deptLoca,
        deptCapa = department.deptCapa
    )
}