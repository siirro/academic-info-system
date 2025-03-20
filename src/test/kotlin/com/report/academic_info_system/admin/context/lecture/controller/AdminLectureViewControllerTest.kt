package com.report.academic_info_system.admin.context.lecture.controller

import com.report.academic_info_system.admin.context.lecture.service.AdminLectureService
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
class AdminLectureViewControllerTest {

    @Mock
    private lateinit var adminLectureService: AdminLectureService  // ✅ `@MockBean` 대신 `@Mock` 사용

    @InjectMocks
    private lateinit var adminLectureViewController: AdminLectureViewController  // ✅ 컨트롤러에 Mock 주입

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.standaloneSetup(adminLectureViewController).build()
    }

    @Test
    fun getLecture() {
        // given
        val mockTable = TableDTO(
            name = "Table",
            columns = listOf("column1", "column2"),
            records = listOf(
                listOf("value1", "value2"),
                listOf("value3", "value4")
            )
        )
        Mockito.`when`(adminLectureService.getLectureTable()).thenReturn(mockTable)

        // when & then
        mockMvc.perform(get("/admin/lecture"))
            .andExpect(status().isOk)
    }

    @Test
    fun isContain() {
        // given
        val mockTable = TableDTO(
            name = "Lecture",
            columns = listOf("id", "name"),
            records = listOf(
                listOf("1", "이산수학"),
                listOf("2", "인공지능")
            )
        )
        Mockito.`when`(adminLectureService.getLectureTable()).thenReturn(mockTable)

        // when & then
        mockMvc.perform(get("/admin/lecture"))
            .andExpect(status().isOk)
            .andExpect(model().attributeExists("table"))
            .andExpect(model().attribute("table", mockTable))
    }

    /**
     * 3️⃣ 서비스가 예외를 발생시킬 경우 적절한 오류 반환 확인
     */
    @Test
    fun getReturnName() {
        // given
        val mockTable = TableDTO(
            name = "Table",
            columns = listOf("column1", "column2"),
            records = listOf(
                listOf("value1", "value2"),
                listOf("value3", "value4")
            )
        )
        Mockito.`when`(adminLectureService.getLectureTable()).thenReturn(mockTable)

        // when & then
        mockMvc.perform(get("/admin/lecture"))
            .andExpect(view().name("admin/page-table"))
    }
}