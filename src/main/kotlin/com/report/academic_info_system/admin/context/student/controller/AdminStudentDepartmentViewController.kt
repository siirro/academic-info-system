package com.report.academic_info_system.admin.context.student.controller

import com.report.academic_info_system.admin.context.student.service.AdminStudentDepartmentService
import com.report.academic_info_system.admin.data.FormElementDTO
import com.report.academic_info_system.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/student/department")
class AdminStudentDepartmentViewController(
    private val adminStudentDepartmentService: AdminStudentDepartmentService
) {
    @GetMapping
    fun studentDepartment(model: Model): String {
        val studentList = adminStudentDepartmentService.getStudentList()
        val departmentList = adminStudentDepartmentService.getDepartmentList()
        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("student", 7, studentList),
            SelectFormElementDTO("department", 5, departmentList)
        )
        model.addAttribute("formElements", formElements)

        val table = adminStudentDepartmentService.getStudentDepartmentTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "StudentDepartment"),
            Pair("pageName", table.name),
            Pair("editable", false),
            Pair("deletable", true),
            Pair("hasDetails", false)
        )
        model.addAttribute("pageAttributes", pageAttributes)

        return "admin/page-table"
    }
}