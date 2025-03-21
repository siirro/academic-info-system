package com.report.academic_info_system.admin.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status) {

    companion object {
        fun successCreate(): ResponseEntity<Any> {
            return ok("DATA CREATE FINISH")
        }

        fun successUpdate(): ResponseEntity<Any> {
            return ok("DATA UPDATE FINISH")
        }

        fun successDelete(): ResponseEntity<Any> {
            return ok("DATA DELETE FINISH")
        }
    }

}