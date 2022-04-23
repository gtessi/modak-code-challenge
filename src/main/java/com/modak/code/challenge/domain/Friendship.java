package com.modak.code.challenge.domain;

import java.util.List;

public class Friendship {

    private final Student student;

    private final List<Student> friends;

    public Friendship(Student student, List<Student> friends) {
        this.student = student;
        this.friends = friends;
    }

    public Student getStudent() {
        return student;
    }

    public List<Student> getFriends() {
        return friends;
    }
}
