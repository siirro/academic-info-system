package com.report.academic_info_system.presentation.controller

import com.report.academic_info_system.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PresentationViewController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/")
    fun index(): String {
        return "presentation/index"
    }

    @GetMapping("/admin")
    fun projects(): String {
        return "presentation/admin"
    }

    @GetMapping("/lecture/list")
    fun lectures(model: org.springframework.ui.Model): String {
        val lectures = presentationService.getLectures()
        model.addAttribute("list", lectures)
        return "presentation/admin/lecture-list"
    }
}