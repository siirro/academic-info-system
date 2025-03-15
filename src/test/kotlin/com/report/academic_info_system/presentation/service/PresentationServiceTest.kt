package com.report.academic_info_system.presentation.service

import com.report.academic_info_system.domain.entity.Student
import com.report.academic_info_system.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {
    @InjectMocks
    lateinit var presentationService: PresentationService
    @Mock
    lateinit var presentationRepository: PresentationRepository

    val DATA_SIZE = 4

    @Test
    fun testGetStudents() {
        //given
        val students = mutableListOf<Student>()
        for(i in 1 .. DATA_SIZE) {
            val student = Student(
                password = "${i}",
                stdNm = "${i}",
                stdResideNo = "${i}",
                stdPhone = "${i}",
                stdYear = i,
                stdStatus = i,
                stdEntryYmd = LocalDate.now()
            )
            students.add(student)
        }

        Mockito.`when`(presentationRepository.getStudents())
            .thenReturn(students)

        //when
        val studentDTOs = presentationService.getStudents()
        //then
        Assertions.assertThat(studentDTOs).hasSize(DATA_SIZE)
    }
}