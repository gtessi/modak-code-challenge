package com.modak.code.challenge.service;

import com.modak.code.challenge.domain.Friendship;
import com.modak.code.challenge.domain.Student;
import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();

    List<Friendship> getAllFriendships();

    Friendship getAllFriendsByStudentId(Integer studentId);
}
