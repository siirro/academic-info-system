package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface StudentRepository : JpaRepository<Student, Long> {
    // fun findAll(): List<Student>

    @Query("select s from Student s left join fetch s.stdDepts where s.id = :id")
    override fun findById(id: Long): Optional<Student>
}