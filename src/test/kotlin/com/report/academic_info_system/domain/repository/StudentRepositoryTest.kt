package com.report.academic_info_system.domain.repository

import com.report.academic_info_system.domain.entity.Department
import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.domain.entity.StudentDepartment
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentRepositoryTest(
    @Autowired val studentRepository : StudentRepository,
    @Autowired val departmentRepository: DepartmentRepository
) {
    val DATA_SIZE = 3

    private fun createStudent(n: Int) : Student {
        val student = Student(
            password = "${n}",
            stdNm = "${n}",
            stdResideNo = "${n}",
            stdPhone = "${n}",
            stdYear = n,
            stdStatus = n,
            stdEntryYmd = LocalDate.now()
        )

        val depts = departmentRepository.findAll()
        val deptsUsedInStudent = depts.subList(0, n)
        for(department in depts) {
            val studentDepartment = StudentDepartment(student = student, department = department, isActive = true)
            student.stdDepts.add(studentDepartment)
        }
        return student
    }

    @BeforeAll
    fun beforeAll() {
        println("1Before 학과 초기화")
        val depts = mutableListOf<Department>()
        for(i in 1 .. DATA_SIZE) {
            val dept = Department(deptNm = "테스트학과 ${i}", deptLoca = "사무실 ${i}호", deptCapa = i)
            depts.add(dept)
        }
        departmentRepository.saveAll(depts)
        println("2After 학과 초기화")

        println("3Before 학생 초기화")
        val students = mutableListOf<Student>()
        for (i in 1 .. DATA_SIZE) {
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
}