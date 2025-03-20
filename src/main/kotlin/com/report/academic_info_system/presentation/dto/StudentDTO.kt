package com.report.academic_info_system.presentation.dto

import com.report.academic_info_system.domain.entity.Student

class StudentDTO(
    val password: String?,
    val stdNm: String,
    val stdResideNo: String,
    val stdPhone: String,
    val stdYear: Int,
    val stdStatus: String,
    val stdEntryYmd: String?,
    val stdDepts: List<DepartmentDTO>
) {
    constructor(student: Student) : this(
        password = student.password,
        stdNm = student.stdNm,
        stdResideNo = student.stdResideNo,
        stdPhone = student.stdPhone,
        stdYear = student.stdYear,
        stdStatus = student.stdStatus,
        stdEntryYmd = student.stdEntryYmd,
        stdDepts = student.stdDepts.filter { it.isActive }.map { it.department }.map { DepartmentDTO(it) }
    )
}