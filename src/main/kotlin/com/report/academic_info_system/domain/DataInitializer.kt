package com.report.academic_info_system.domain

import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.entity.StudentDepartment
import com.report.academic_info_system.domain.repository.DepartmentRepository
import com.report.academic_info_system.domain.repository.StudentRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository
) {

    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터 주입")

        val dept1 = Department(deptNm = "컴퓨터과학과", deptLoca = "본관 305호", deptCapa = 1100)
        val dept2 = Department(deptNm = "일본학과", deptLoca = "인문관 503호", deptCapa = 1500)
        val dept3 = Department(deptNm = "데이터통계학과", deptLoca = "공학관 401호", deptCapa = 800)
        departmentRepository.saveAll(mutableListOf(dept1, dept2, dept3))

        val student1 = Student(
            password = "1234",
            stdNm = "김준서",
            stdResideNo = "980512-1546113",
            stdPhone = "01012345678",
            stdYear = 3,
            stdStatus = 0,
            stdEntryYmd = LocalDate.now(),
        )
        student1.stdDepts.addAll(
            mutableListOf(
                StudentDepartment(student = student1, department = dept1, isActive = true),
                StudentDepartment(student = student1, department = dept2, isActive = true)
            )
        )

        val student2 = Student(
            password = "1234",
            stdNm = "성한빈",
            stdResideNo = "010613-3113133",
            stdPhone = "01012345678",
            stdYear = 1,
            stdStatus = 0,
            stdEntryYmd = LocalDate.now(),
        )
        student2.stdDepts.addAll(
            mutableListOf(
                StudentDepartment(student = student2, department = dept1, isActive = false),
                StudentDepartment(student = student2, department = dept2, isActive = true)
            )
        )

        studentRepository.saveAll(mutableListOf(student1, student2))
    }
}