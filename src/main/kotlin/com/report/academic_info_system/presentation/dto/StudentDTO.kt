package com.report.academic_info_system.presentation.dto

import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.entity.Student
import java.time.LocalDate

class StudentDTO(
    val password: String?,
    val stdNm: String,
    val stdResideNo: String,
    val stdPhone: String,
    val stdYear: Int,
    val stdStatus: Int,
    val stdEntryYmd: LocalDate?,
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