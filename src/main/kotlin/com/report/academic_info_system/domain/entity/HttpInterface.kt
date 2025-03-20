package com.report.academic_info_system.domain.entity

import jakarta.persistence.*
import jakarta.servlet.http.HttpServletRequest

@Entity
class HttpInterface(httpServletRequest: HttpServletRequest) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "http_interface_id")
    var id: Long? = null

    var cookies: String? = httpServletRequest.cookies
        ?.map { "${it.name}:${it.value}" }
        ?.toString()

    // 이 웹의 링크를 누른 사이트
    var referer: String? = httpServletRequest.getHeader("referer")

    var localAddr: String? = httpServletRequest.localAddr

    var remoteAddr: String? = httpServletRequest.remoteAddr

    var remoteHost: String? = httpServletRequest.remoteHost

    var requestUri: String? = httpServletRequest.requestURI

    // 브라우저 정보(ex. Chrome, Safari, Edge, Mobile)
    var userAgent: String? = httpServletRequest.getHeader("user-agent")

}