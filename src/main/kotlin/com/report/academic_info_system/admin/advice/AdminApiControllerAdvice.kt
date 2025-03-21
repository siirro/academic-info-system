package com.report.academic_info_system.admin.advice

import com.report.academic_info_system.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AdminApiControllerAdvice {
    val log = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String> {
        log.info(e.message, e)
        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        log.info(e.message, e)
        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field} ${fieldError.defaultMessage}]"

        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleConstraintViolation(ex: DataIntegrityViolationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 데이터 입니다.")
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException::class)
    fun handleInvalidDataAccessApiUsageException(ex: InvalidDataAccessApiUsageException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("하위 데이터를 삭제해주세요.")
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        log.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템 오류가 발생했습니다.")
    }
}