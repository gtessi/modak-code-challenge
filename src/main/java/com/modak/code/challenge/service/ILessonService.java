package com.modak.code.challenge.service;

import com.modak.code.challenge.dto.StudentLessonHistoryDto;

public interface ILessonService {

    StudentLessonHistoryDto getLessonsByStudentId(Integer studentId);
}
