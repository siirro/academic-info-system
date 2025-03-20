package com.report.academic_info_system.admin.context.student.controller

import com.report.academic_info_system.admin.context.student.service.AdminStudentService
import com.report.academic_info_system.admin.data.TableDTO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class AdminStudentDepartmentViewControllerTest {
    @Mock
    private lateinit var adminStudentService: AdminStudentService

    @InjectMocks
    private lateinit var adminStudentViewController: AdminStudentViewController

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.standaloneSetup(adminStudentViewController).build()
    }

    @Test
    fun getStudent() {
        val mockTable = TableDTO(
            name = "Student",
            columns = listOf("col1", "col2", "col3"),
            records = listOf(
                listOf("val1", "val2", "val3"),
                listOf("val4", "val5", "val6"),
            )
        )
        Mockito.`when`(adminStudentService.getStudentTable()).thenReturn(mockTable)

        mockMvc.perform(get("/admin/student"))
            .andExpect(status().isOk)
            .andExpect(model().attributeExists("table"))
            .andExpect(model().attribute("table", mockTable))
            .andExpect(view().name("admin/page-table"))

    }
}