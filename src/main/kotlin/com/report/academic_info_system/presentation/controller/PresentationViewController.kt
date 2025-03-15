package com.report.academic_info_system.presentation.controller

import ch.qos.logback.core.model.Model
import com.report.academic_info_system.presentation.service.PresentationService
import org.springframework.stereotype.Controller
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
    fun index(model: Model): String {
        return "presentation/index"
    }

    @GetMapping("/projects")
    fun projects(model: Model): String {
        return "presentation/projects"
    }
}