package com.modak.code.challenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {

    private final Integer id;

    private final String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getFullName() {
        return String.format("%d - %s", this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        Student s = (Student) obj;
        return id.equals(s.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
