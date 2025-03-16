package com.report.academic_info_system.domain.entity

import jakarta.persistence.*

@Entity
class StudentLecture(
    student: Student, lecture: Lecture, totalScore: Int?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_lect_id")
    var id: Long? = null

    @ManyToOne(targetEntity = Student::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "std_id", nullable = false)
    var student: Student = student

    @ManyToOne(targetEntity = Lecture::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id", nullable = false)
    var lecture: Lecture = lecture

    var totalScore: Int? = totalScore
}