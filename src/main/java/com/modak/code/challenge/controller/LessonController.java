package com.modak.code.challenge.controller;

import com.modak.code.challenge.dto.StudentLessonHistoryDto;
import com.modak.code.challenge.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping("/lesson/{studentId}")
    public ResponseEntity<StudentLessonHistoryDto> getLessonsByStudentId(
            @PathVariable("studentId") Integer studentId
    ) {
        if (studentId == null) {
            return ResponseEntity.badRequest().build();
        }

        StudentLessonHistoryDto result = this.service.getLessonsByStudentId(studentId);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
