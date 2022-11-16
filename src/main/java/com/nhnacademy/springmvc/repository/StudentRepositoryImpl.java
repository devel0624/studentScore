package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> students = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return students.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {

        long id = students.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Student student = Student.create(id, name, email, score, comment);

        students.put(student.getId(),student);

        return student;
    }

    @Override
    public Student getStudent(long id) {
        return students.get(id);
    }

    @Override
    public Student modify(long studentId, StudentModifyRequest request) {
        Student editStudent = getStudent(studentId);

        editStudent.setName(request.getName());
        editStudent.setEmail(request.getEmail());
        editStudent.setScore(request.getScore());
        editStudent.setComment(request.getComment());

        return editStudent;
    }
}
