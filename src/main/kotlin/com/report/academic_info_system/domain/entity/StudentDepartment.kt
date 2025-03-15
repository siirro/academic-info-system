package com.report.academic_info_system.domain.entity

import jakarta.persistence.*

@Entity
class StudentDepartment(
    student: Student, department: Department, isActive: Boolean
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_dept_id")
    var id: Long? = null

    @ManyToOne(targetEntity = Student::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "std_id", nullable = false)
    var student: Student = student

    @ManyToOne(targetEntity = Department::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", nullable = false)
    var department: Department = department

    var isActive: Boolean = isActive
}