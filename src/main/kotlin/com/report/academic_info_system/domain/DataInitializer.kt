package com.report.academic_info_system.domain

import com.report.academic_info_system.domain.entity.*
import com.report.academic_info_system.domain.repository.DepartmentRepository
import com.report.academic_info_system.domain.repository.LectureRepository
import com.report.academic_info_system.domain.repository.StudentRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val lectureRepository: LectureRepository
) {

    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터 주입")

        val dept1 = Department(deptNm = "컴퓨터과학과", deptLoca = "본관 305호", deptCapa = 1100)
        val dept2 = Department(deptNm = "일본학과", deptLoca = "인문관 503호", deptCapa = 1500)
        val dept3 = Department(deptNm = "데이터통계학과", deptLoca = "공학관 401호", deptCapa = 800)
        departmentRepository.saveAll(mutableListOf(dept1, dept2, dept3))

        val lect1 = Lecture(
            lectNm = "이산수학",
            lectRoom = "서부센터 505호",
            proId = null,
            lectCapa = 150,
            lectDay = "월,화",
            lectHour = 3,
            lectYear = 2,
            deptId = null,
            isActive = true
        )

        val lect2 = Lecture(
            lectNm = "운영체제",
            lectRoom = "남부센터 301호",
            proId = 152431,
            lectCapa = 300,
            lectDay = "금",
            lectHour = 3,
            lectYear = 3,
            deptId = null,
            isActive = true
        )

        val lect3 = Lecture(
            lectNm = "그래픽커뮤니케이션",
            lectRoom = "남부센터 208호",
            proId = 152431,
            lectCapa = 300,
            lectDay = "수",
            lectHour = 3,
            lectYear = 1,
            deptId = null,
            isActive = true
        )
        lectureRepository.saveAll(mutableListOf(lect1, lect2, lect3))

        val student1 = Student(
            password = "1234",
            stdNm = "김준서",
            stdResideNo = "980512-1546113",
            stdPhone = "01012345678",
            stdYear = 3,
            stdStatus = "승인대기",
            stdEntryYmd = LocalDate.now().toString(),
        )
        student1.stdDepts.addAll(
            mutableListOf(
                StudentDepartment(student = student1, department = dept1, isActive = true),
                StudentDepartment(student = student1, department = dept2, isActive = true)
            )
        )

        student1.stdLects.addAll(
            mutableListOf(
                StudentLecture(student = student1, lecture = lect1, totalScore = null),
                StudentLecture(student = student1, lecture = lect2, totalScore = null)
            )
        )

        val student2 = Student(
            password = "1234",
            stdNm = "성한빈",
            stdResideNo = "010613-3113133",
            stdPhone = "01012345678",
            stdYear = 1,
            stdStatus = "재학",
            stdEntryYmd = LocalDate.now().toString(),
        )
        student2.stdDepts.addAll(
            mutableListOf(
                StudentDepartment(student = student2, department = dept1, isActive = false),
                StudentDepartment(student = student2, department = dept2, isActive = true)
            )
        )

        student2.stdLects.addAll(
            mutableListOf(
                StudentLecture(student = student2, lecture = lect2, totalScore = null)
            )
        )

        studentRepository.saveAll(mutableListOf(student1, student2))
    }
}