package com.modak.code.challenge.dto;

import com.modak.code.challenge.domain.Lesson;

public class LessonCountDto {

    private final Lesson lesson;

    private final Integer count;

    public LessonCountDto(Lesson lesson, Integer count) {
        this.lesson = lesson;
        this.count = count;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Integer getCount() {
        return count;
    }
}
