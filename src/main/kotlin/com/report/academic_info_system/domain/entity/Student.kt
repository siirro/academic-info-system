package com.report.academic_info_system.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Student(
    password: String?,
    stdNm: String,
    stdResideNo: String,
    stdPhone: String,
    stdYear: Int,
    stdStatus: Int,
    stdEntryYmd: LocalDate?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_id")
    var id: Long? = null

    @Column(name = "std_pw")
    var password: String? = password

    var stdNm: String = stdNm

    var stdResideNo: String = stdResideNo

    var stdPhone: String = stdPhone

    var stdYear: Int = stdYear

    var stdStatus: Int = stdStatus

    var stdEntryYmd: LocalDate? = stdEntryYmd

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = [(CascadeType.PERSIST)])
    var stdDepts: MutableList<StudentDepartment> = mutableListOf()

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = [(CascadeType.PERSIST)])
    var stdLects: MutableList<StudentLecture> = mutableListOf()
}