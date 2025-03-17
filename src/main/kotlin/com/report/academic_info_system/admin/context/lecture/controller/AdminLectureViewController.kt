package com.report.academic_info_system.admin.context.lecture.controller

import com.report.academic_info_system.admin.context.lecture.service.AdminLectureViewService
import com.report.academic_info_system.admin.data.FormElementDTO
import com.report.academic_info_system.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/lecture")
class AdminLectureViewController(private val adminLectureViewService: AdminLectureViewService) {
    @GetMapping
    fun lecture(model: org.springframework.ui.Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("lectNm", 4),
            TextFormElementDTO("lectRoom", 4),
            TextFormElementDTO("lectDay", 2),
            TextFormElementDTO("lectHour", 2)
        )

        model.addAttribute("formElements", formElements)

        val table = adminLectureViewService.getLectureTable()
        model.addAttribute("table", table)
        model.addAttribute("department", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Lecture"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", true),
            Pair("hashDetails", true),
        )
        model.addAttribute(pageAttributes)
        return "admin/page-table"
    }

}