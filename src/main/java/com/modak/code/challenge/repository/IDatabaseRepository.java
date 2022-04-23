package com.modak.code.challenge.repository;

import com.modak.code.challenge.domain.Lesson;
import com.modak.code.challenge.domain.Student;
import java.util.List;
import java.util.Map;

public interface IDatabaseRepository {

    Student getStudentById(Integer studentId);

    List<Student> getAllStudents();

    List<Student> getFriendsByStudentId(Integer studentId);

    void addStudent(String name);

    void addFriendship(Integer studentId, Integer friendId);

    List<Lesson> getAllLessons();

    Map<Integer, Integer> getLessonsByStudentId(Integer studentId);
}
