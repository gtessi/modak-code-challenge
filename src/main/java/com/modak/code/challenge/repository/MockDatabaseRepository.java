package com.modak.code.challenge.repository;

import com.modak.code.challenge.domain.Lesson;
import com.modak.code.challenge.domain.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

@Repository
public class MockDatabaseRepository implements IDatabaseRepository {

    private Set<Student> students = new HashSet<>();

    private Map<Integer, Set<Integer>> friendships = new HashMap<>();

    private Set<Lesson> lessons = new HashSet<>();

    private Map<Integer, Map<Integer, Integer>> studentLessonCounter = new HashMap<>();

    public MockDatabaseRepository() {
        initializeStudents();
        initializeFriendships();
        initializeLessons();
        initializeStudentLessonCount();
    }

    public Student getStudentById(Integer studentId) {
        return this.students.stream()
                .filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(this.students);
    }

    public List<Student> getFriendsByStudentId(Integer studentId) {
        List<Student> result = new ArrayList<>();

        Set<Integer> friends = this.friendships.get(studentId);

        if (friends != null) {
            friends.forEach(friendId ->
                    this.students.stream()
                            .filter(student -> student.getId().equals(friendId))
                            .findAny().ifPresent(result::add)
            );
        }

        return result;
    }

    public void addStudent(String name) {
        Student lastStudent =  this.students.stream()
                .max(Comparator.comparing(Student::getId))
                .orElse(null);

        Integer newId = lastStudent != null ? lastStudent.getId() + 1 : 1;

        this.students.add(new Student(newId, name));
    }

    public void addFriendship(Integer studentId, Integer friendId) {
        Set<Integer> friendsSource = this.friendships.get(studentId);
        friendsSource.add(friendId);

        this.friendships.put(studentId, friendsSource);

        Set<Integer> friendsDestiny = this.friendships.get(friendId);
        friendsDestiny.add(studentId);

        this.friendships.put(friendId, friendsDestiny);
    }

    public List<Lesson> getAllLessons() {
        return new ArrayList<>(this.lessons);
    }

    public Map<Integer, Integer> getLessonsByStudentId(Integer studentId) {
        return this.studentLessonCounter.get(studentId);
    }

    // PRIVATE METHODS
    private void initializeStudents() {
        Student student1 = new Student(1, "Joe");
        Student student2 = new Student(2, "Mark");
        Student student3 = new Student(3, "Jody");
        Student student4 = new Student(4, "Rachel");
        Student student5 = new Student(5, "Michael");
        Student student6 = new Student(6, "Jenny");

        this.students.add(student1);
        this.students.add(student2);
        this.students.add(student3);
        this.students.add(student4);
        this.students.add(student5);
        this.students.add(student6);
    }

    private void initializeFriendships() {
        this.friendships.put(getStudentById(1).getId(),
                Stream.of(
                        getStudentById(2).getId(),
                        getStudentById(3).getId(),
                        getStudentById(4).getId()
                ).collect(Collectors.toSet())
        );

        this.friendships.put(getStudentById(2).getId(),
                Stream.of(
                        getStudentById(1).getId()
                ).collect(Collectors.toSet())
        );

        this.friendships.put(getStudentById(3).getId(),
                Stream.of(
                        getStudentById(1).getId()
                ).collect(Collectors.toSet())
        );

        this.friendships.put(getStudentById(4).getId(),
                Stream.of(
                        getStudentById(1).getId()
                ).collect(Collectors.toSet())
        );
    }

    private void initializeLessons() {
        Lesson math = new Lesson(1, "math");
        Lesson spanish = new Lesson(2, "spanish");
        Lesson physics = new Lesson(3, "physics");
        Lesson music = new Lesson(4, "music");

        this.lessons.add(math);
        this.lessons.add(spanish);
        this.lessons.add(physics);
        this.lessons.add(music);
    }

    private void initializeStudentLessonCount() {
        // Joe id = 1
        Map<Integer, Integer> joeLessons = new HashMap<>();
        joeLessons.put(1, 3); // Math, 3 lessons
        joeLessons.put(3, 1); // Physics, 1 lesson
        joeLessons.put(4, 5); // Music, 5 lessons

        this.studentLessonCounter.put(1, joeLessons);

        // Rachel id = 4
        Map<Integer, Integer> rachelLessons = new HashMap<>();
        rachelLessons.put(1, 3); // Math, 3 lessons
        rachelLessons.put(2, 2); // Spanish, 2 lessons

        this.studentLessonCounter.put(4, rachelLessons);
    }
}
