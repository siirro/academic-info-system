package com.report.academic_info_system.admin.context.student.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.report.academic_info_system.admin.context.student.form.StudentForm
import com.report.academic_info_system.domain.repository.StudentRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertNotNull

@SpringBootTest
@AutoConfigureMockMvc
class AdminStudentApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Test
    @DisplayName("[삽입1] 학생 정보 삽입")
    fun postStudent() {
        val form = StudentForm(
            password = "1234",
            stdNm = "김기영",
            stdResideNo = "000101-3834567",
            stdPhone = "010-1234-5678",
            stdYear = 3,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("DATA CREATE FINISH"))

        val students = studentRepository.findAll()
        val savedStudent = students.find { it.stdNm == "김기영" }
        assertNotNull(savedStudent, "김기영 학생이 저장되지 않았습니다.")

        students.forEach { println(it.stdNm) }
    }

    @Test
    @DisplayName("[삽입2] 필수값 조건 실패")
    fun postStudentWithEmptyName() {
        val form = StudentForm(
            password = "1234",
            stdNm = "",
            stdResideNo = "000101-3234567",
            stdPhone = "010-1234-5678",
            stdYear = 3,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("[stdNm 필수값입니다.]"))

    }

    @Test
    @DisplayName("[삽입3] 중복된 주민등록번호")
    fun postDuplicateResideNoTest() {
        val form = StudentForm(
            password = "1234",
            stdNm = "김기영",
            stdResideNo = "000101-3294567",
            stdPhone = "010-1234-5678",
            stdYear = 3,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("DATA CREATE FINISH"))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("중복된 데이터 입니다."))
    }

    @Test
    @DisplayName("[수정1] 학생 정보 수정")
    fun putStudent() {
        val students1 = studentRepository.findAll()
        students1.forEach { println("수정 전 학생목록 ${it.id} ${it.stdNm}") }

        val form = StudentForm(
            password = "1234",
            stdNm = "김수영",
            stdResideNo = "000101-3234567",
            stdPhone = "010-1234-5678",
            stdYear = 3,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        val studentId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.put("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("DATA UPDATE FINISH"))

        val students = studentRepository.findAll()
        students.forEach { println("${it.id} ${it.stdNm}") }
    }

    @Test
    @DisplayName("[수정2] 존재하지 않는 학생 ID")
    fun putStudentNotFoundId() {
        val studentId = 9L

        val form = StudentForm(
            password = "1234",
            stdNm = "김수영",
            stdResideNo = "000101-3234567",
            stdPhone = "010-1234-5678",
            stdYear = 3,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.put("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("ID 9에 해당하는 데이터가 없습니다."))
    }

    @Test
    @DisplayName("[수정3] 학년 최대값 초과")
    fun putStudentWithInvalidYear() {
        val studentId = 2L

        val form = StudentForm(
            password = "1234",
            stdNm = "김수영",
            stdResideNo = "000101-3234567",
            stdPhone = "010-1234-5678",
            stdYear = 6,
            stdStatus = "재학",
            stdEntryYmd = "2023-03-02"
        )

        mockMvc.perform(
            MockMvcRequestBuilders.put("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(form))
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("[stdYear 최대값은 4입니다.]"))
    }

    @Test
    @DisplayName("[삭제1] 학생 정보 삭제")
    fun deleteStudentSuccessfully() {
        val studentId = 3L

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("DATA DELETE FINISH"))

        val student = studentRepository.findById(studentId)
        Assertions.assertThat(student.isEmpty).isTrue()
    }

    @Test
    @DisplayName("[삭제2] 존재하지 않는 학생 ID")
    fun deleteStudentNotFound() {
        val studentId = 8L

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("ID 8에 해당하는 데이터가 없습니다."))
    }

    @Test
    @DisplayName("[삭제3] 외래키 제한")
    fun deleteStudentWithConstraintFailure() {
        val studentId = 1L

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/admin/api/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("하위 데이터를 삭제해주세요."))
    }
}