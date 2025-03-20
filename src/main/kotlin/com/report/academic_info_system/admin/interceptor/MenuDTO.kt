package com.report.academic_info_system.admin.interceptor

import org.hibernate.query.Page

data class MenuDTO(
    val name: String,
    val pages: List<Page>
)