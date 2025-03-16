package com.report.academic_info_system.presentation.dto

import com.report.academic_info_system.domain.entity.Lecture

class LectureDTO(
    var lectNm: String,
    var lectRoom: String?,
    var proId: Long?,
    var lectCapa: Int?,
    var lectDay: String?,
    var lectHour: Int?,
    var lectYear: Int?,
    var deptId: Long?
) {
    constructor(lecture: Lecture) : this(
        lectNm = lecture.lectNm,
        lectRoom = lecture.lectRoom,
        proId = lecture.proId,
        lectCapa = lecture.lectCapa,
        lectDay = lecture.lectDay,
        lectHour = lecture.lectHour,
        lectYear = lecture.lectYear,
        deptId = lecture.deptId
    )
}