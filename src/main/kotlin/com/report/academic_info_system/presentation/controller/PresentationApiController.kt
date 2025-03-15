package com.report.academic_info_system.presentation.controller

import com.report.academic_info_system.presentation.dto.StudentDTO
import com.report.academic_info_system.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/v1/students")
    fun getStudents(): List<StudentDTO> {
        return presentationService.getStudents()
    }
}