package com.modak.code.challenge.controller;

import com.modak.code.challenge.domain.Friendship;
import com.modak.code.challenge.domain.Student;
import com.modak.code.challenge.service.StudentService;
import java.security.InvalidParameterException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students/list")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> result = this.service.getAllStudents();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/friends/list")
    public ResponseEntity<List<Friendship>> getAllFriendships() {
        List<Friendship> result = this.service.getAllFriendships();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/friends/{studentId}")
    public ResponseEntity<Friendship> getAllFriendsByStudentId(
            @PathVariable("studentId") Integer studentId
    ) {
        if (studentId == null) {
            return ResponseEntity.badRequest().build();
        }

        Friendship result = this.service.getAllFriendsByStudentId(studentId);

        if (result.getStudent() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
