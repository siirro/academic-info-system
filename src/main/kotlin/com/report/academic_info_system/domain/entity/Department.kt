package com.report.academic_info_system.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Department(
    deptNm: String,
    deptLoca: String?,
    deptCapa: Int?
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    var id: Long? = null

    var deptNm: String = deptNm

    var deptLoca: String? = deptLoca

    var deptCapa: Int? = deptCapa
}