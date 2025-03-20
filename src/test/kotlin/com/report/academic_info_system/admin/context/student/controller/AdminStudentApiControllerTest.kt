package com.report.academic_info_system.admin.context.student.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.report.academic_info_system.admin.context.student.form.StudentForm
import com.report.academic_info_system.admin.context.student.service.AdminStudentService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.awt.PageAttributes.MediaType

@ExtendWith(MockitoExtension::class)
class AdminStudentApiControllerTest {

    @Mock
    private lateinit var adminStudentService: AdminStudentService

    @InjectMocks
    private lateinit var adminStudentApiController: AdminStudentApiController

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.standaloneSetup(adminStudentApiController).build()
    }

    private val objectMapper = ObjectMapper()

    // PostMapping 테스트
    @Test
    fun postProjectTest() {
        val form = StudentForm()

        mockMvc.perform(
            MockMvcRequestBuilders.post("/admin/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form))
        )
            .andExpect(status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("성공적으로 생성되었습니다"))
    }

    // PutMapping 테스트
    @Test
    fun putProjectTest() {
        val form = StudentForm(/* 필요한 필드 값들 */)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/admin/api/student/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form))
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("성공적으로 업데이트되었습니다"))
    }

    // DeleteMapping 테스트
    @Test
    fun deleteStudentDepartmentTest() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/api/student/{id}", 1L))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("성공적으로 삭제되었습니다"))
    }