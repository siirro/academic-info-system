package com.report.academic_info_system.admin.context.department.controller

import com.report.academic_info_system.admin.context.department.service.AdminDepartmentService
import com.report.academic_info_system.admin.data.FormElementDTO
import com.report.academic_info_system.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/department")
class AdminDepartmentViewController(private val adminDepartmentService: AdminDepartmentService) {
    @GetMapping
    fun department(model: org.springframework.ui.Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("deptNm", 5),
            TextFormElementDTO("deptLoca", 5),
            TextFormElementDTO("deptCapa", 2)
        )

        model.addAttribute("formElements", formElements)

        val table = adminDepartmentService.getDepartmentTable()
        model.addAttribute("table", table)
        model.addAttribute("department", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Department"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", true),
            Pair("hashDetails", true),
        )
        model.addAttribute(pageAttributes)
        return "admin/page-table"
    }

}