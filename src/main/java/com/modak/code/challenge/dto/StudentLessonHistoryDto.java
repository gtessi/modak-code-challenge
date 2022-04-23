package com.modak.code.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modak.code.challenge.domain.Student;

import java.util.List;

public class StudentLessonHistoryDto {

    private final Student student;

    @JsonProperty("lessons_count")
    private final List<LessonCountDto> lessonsCount;

    public StudentLessonHistoryDto(Student student, List<LessonCountDto> lessonsCount) {
        this.student = student;
        this.lessonsCount = lessonsCount;
    }

    public Student getStudent() {
        return student;
    }

    public List<LessonCountDto> getLessonsCount() {
        return lessonsCount;
    }
}
