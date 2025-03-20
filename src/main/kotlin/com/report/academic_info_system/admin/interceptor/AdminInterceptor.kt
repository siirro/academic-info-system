package com.report.academic_info_system.admin.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class AdminInterceptor : HandlerInterceptor {
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val menus = listOf<MenuDTO>(
            MenuDTO(
                name = "Admin Index",
                pages = listOf<PageDTO>(
                    PageDTO(name = "Introduction", url = "/admin/introduction"),
                    PageDTO(name = "Link", url = "/admin/link")
                )
            ),
            MenuDTO(
                name = "Lecture",
                pages = listOf<PageDTO>(
                    PageDTO(name = "lecture", url = "/admin/lecture")
                )
            ),
            MenuDTO(
                name = "Student",
                pages = listOf<PageDTO>(
                    PageDTO(name = "Student", url = "/admin/student"),
                    PageDTO(name = "StudentDeparture", url = "/admin/student/departure")
                )
            ),
        )

        modelAndView?.model?.put("menus", menus)
    }
}