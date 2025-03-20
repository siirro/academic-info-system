package com.report.academic_info_system.admin.context.student.controller

import com.report.academic_info_system.admin.context.student.service.AdminStudentService
import com.report.academic_info_system.admin.data.FormElementDTO
import com.report.academic_info_system.admin.data.SelectFormElementDTO
import com.report.academic_info_system.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/student")
class AdminStudentViewController(
    private val adminStudentService: AdminStudentService,
) {
    @GetMapping
    fun student(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("stdNm", 3),
            TextFormElementDTO("stdResideNo", 4),
            SelectFormElementDTO("stdYear", 2, (1..4).toList()),
            SelectFormElementDTO("stdStatus", 3, listOf("재학", "휴학", "제적", "자퇴", "졸업"))
        )
        model.addAttribute("formElements", formElements)

        val table = adminStudentService.getStudentTable()
        model.addAttribute("table", table)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Student"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", true)
        )
        model.addAttribute("pageAttributes", pageAttributes)

        return "admin/page-table"
    }
}