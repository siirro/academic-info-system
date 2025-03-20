package com.siirro.portfolio.presentation.interceptor

import com.report.academic_info_system.domain.entity.HttpInterface
import com.report.academic_info_system.domain.repository.HttpInterfaceRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class PresentationInterceptor(
    private val httpInterfaceRepository: HttpInterfaceRepository
) : HandlerInterceptor {

    // pre는 컨트롤러 가기전, post는 컨트롤러 갔다온후(컨트롤러에서 예외받아오면 post는 발동x), after도 컨트롤러 갔다온후(예외가 발생해도 수행)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val httpInterface = HttpInterface(request)
        httpInterfaceRepository.save(httpInterface)
    }
}