package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentRepositoryTest(
    @Autowired val studentRepository: StudentRepository,
    @Autowired val departmentRepository: DepartmentRepository,
    @Autowired val lectureRepository: LectureRepository
) {
    val DATA_SIZE = 5

    private fun createStudent(n: Int): Student {
        val student = Student(
            password = "${n}",
            stdNm = "${n}",
            stdResideNo = "${n}",
            stdPhone = "${n}",
            stdYear = n,
            stdStatus = "${n}",
            stdEntryYmd = ""
        )

        val depts = departmentRepository.findAll()
        val deptsUsedInStudent = depts.subList(0, n)
        for (department in deptsUsedInStudent) {
            val studentDepartment = StudentDepartment(student = student, department = department, isActive = true)
            student.stdDepts.add(studentDepartment)
        }

        val lects = lectureRepository.findAll()
        val lectsUsedInStudent = lects.subList(0, n)
        for (lecture in lectsUsedInStudent) {
            val studentLecture = StudentLecture(student = student, lecture = lecture, totalScore = null)
            student.stdLects.add(studentLecture)
        }

        return student
    }

    @BeforeAll
    fun beforeAll() {
        println("1Before 학과 초기화")
        val depts = mutableListOf<Department>()
        for (i in 1..DATA_SIZE) {
            val dept = Department(deptNm = "테스트학과 ${i}", deptLoca = "사무실 ${i}호", deptCapa = i)
            depts.add(dept)
        }
        departmentRepository.saveAll(depts)
        println("2After 학과 초기화")

        println("3Before 강좌 초기화")
        val lects = mutableListOf<Lecture>()
        for (i in 1..DATA_SIZE) {
            val lect = Lecture(
                lectNm = "${i}",
                lectRoom = "${i}",
                proId = i.toLong(),
                lectCapa = i,
                lectDay = "${i}",
                lectHour = i,
                lectYear = i,
                deptId = null,
                isActive = true
            )
            lects.add(lect)
        }
        lectureRepository.saveAll(lects)
        println("4After 강좌 초기화")

        println("5Before 학생 초기화")
        val students = mutableListOf<Student>()
        for (i in 1..DATA_SIZE) {
            val student = createStudent(i)
            students.add(student)
        }
        studentRepository.saveAll(students)
        println("4After 학생 초기화")
    }

    @Test
    fun testFindAllStudents() {
        println(" ---------findAll 테스트 시작----------")
        val students = studentRepository.findAll()
        Assertions.assertThat(students).hasSize(DATA_SIZE)
        println("students size : ${students.size}")
    }

    @Test
    fun testFindAllLectures() {
        println(" --------------findLecture-----------------")
        val lectures = lectureRepository.findAll()
        Assertions.assertThat(lectures).hasSize(DATA_SIZE)
        println("lectures size : ${lectures.size}")
    }
}