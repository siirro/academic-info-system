package com.report.academic_info_system.presentation.service

import com.report.academic_info_system.presentation.dto.StudentDTO
import com.report.academic_info_system.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    @Transactional(readOnly = true)
    fun getStudents(): List<StudentDTO> {
        val students = presentationRepository.getStudents()
        return students.map { StudentDTO(it) }
    }
}