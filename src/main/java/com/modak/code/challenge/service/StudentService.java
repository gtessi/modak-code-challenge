package com.modak.code.challenge.service;

import com.modak.code.challenge.domain.Friendship;
import com.modak.code.challenge.domain.Student;
import com.modak.code.challenge.repository.IDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    private IDatabaseRepository repository;

    public StudentService(IDatabaseRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return this.repository.getAllStudents();
    }

    public List<Friendship> getAllFriendships() {
        List<Student> students = this.repository.getAllStudents();

        List<Friendship> result = new ArrayList<>();

        students.forEach(student -> {
            Friendship friendship = new Friendship(student, this.repository.getFriendsByStudentId(student.getId()));

            result.add(friendship);
        });

        return result;
    }

    public Friendship getAllFriendsByStudentId(Integer studentId) {
        Student student = this.repository.getStudentById(studentId);

        List<Student> friends = this.repository.getFriendsByStudentId(studentId);

        Friendship friendship = new Friendship(student, friends);

        return friendship;
    }
}
