package com.report.academic_info_system.domain.entity

import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["stdResideNo"])
    ]
)
class Student(
    password: String?,
    stdNm: String,
    stdResideNo: String,
    stdPhone: String,
    stdYear: Int,
    stdStatus: String,
    stdEntryYmd: String?
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

    var stdStatus: String = stdStatus

    var stdEntryYmd: String? = stdEntryYmd

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = [(CascadeType.PERSIST)])
    var stdDepts: MutableList<StudentDepartment> = mutableListOf()

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = [(CascadeType.PERSIST)])
    var stdLects: MutableList<StudentLecture> = mutableListOf()

    fun update(
        password: String?,
        stdNm: String,
        stdResideNo: String,
        stdPhone: String,
        stdYear: Int,
        stdStatus: String,
        stdEntryYmd: String?
    ) {
        this.password = password
        this.stdNm = stdNm
        this.stdResideNo = stdResideNo
        this.stdPhone = stdPhone
        this.stdYear = stdYear
        this.stdStatus = stdStatus
        this.stdEntryYmd = stdEntryYmd
    }
}