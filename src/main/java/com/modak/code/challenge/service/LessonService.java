package com.modak.code.challenge.service;

import com.modak.code.challenge.domain.Lesson;
import com.modak.code.challenge.dto.LessonCountDto;
import com.modak.code.challenge.domain.Student;
import com.modak.code.challenge.dto.StudentLessonHistoryDto;
import com.modak.code.challenge.repository.IDatabaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LessonService implements ILessonService {

    private IDatabaseRepository repository;

    public LessonService(IDatabaseRepository repository) {
        this.repository = repository;
    }

    public StudentLessonHistoryDto getLessonsByStudentId(Integer studentId) {
        Student student = this.repository.getStudentById(studentId);

        if (student != null) {
            List<LessonCountDto> lessonCountDtos = new ArrayList<>();

            List<Lesson> lessons = this.repository.getAllLessons();

            Map<Integer, Integer> studentLessons = this.repository.getLessonsByStudentId(studentId);

            if (studentLessons != null) {
                studentLessons.forEach((lessonId, count) ->
                    lessons.stream()
                            .filter(x -> lessonId.equals(x.getId()))
                            .findFirst()
                            .ifPresent(lesson -> lessonCountDtos.add(new LessonCountDto(lesson, count)))
                );
            }

            return new StudentLessonHistoryDto(student, lessonCountDtos);
        } else {
            return null;
        }
    }
}
