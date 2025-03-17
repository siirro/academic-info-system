package com.report.academic_info_system.admin.context.lecture.service

import com.report.academic_info_system.admin.data.TableDTO
import com.report.academic_info_system.domain.entity.Lecture
import com.report.academic_info_system.domain.repository.LectureRepository
import org.springframework.stereotype.Service

@Service
class AdminLectureViewService(
    private val lectureRepository: LectureRepository
) {
    fun getLectureTable(): TableDTO {
        val classInfo = Lecture::class
        val entities = lectureRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}