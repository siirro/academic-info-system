package com.report.academic_info_system.domain.entity

import jakarta.persistence.*

@Entity
class Lecture(
    lectNm: String,
    lectRoom: String?,
    proId: Long?,
    lectCapa: Int?,
    lectDay: String?,
    lectHour: Int?,
    lectYear: Int?,
    deptId: Long?,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rect_id")
    var id: Long? = null

    var lectNm: String = lectNm
    var lectRoom: String? = lectRoom
    var proId: Long? = proId
    var lectCapa: Int? = lectCapa
    var lectDay: String? = lectDay
    var lectHour: Int? = lectHour
    var lectYear: Int? = lectYear
    var deptId: Long? = deptId
    var isActive: Boolean = isActive

}