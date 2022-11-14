package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public boolean exists(long id) {
        return false;
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        return null;
    }

    @Override
    public Student getStudent(long id) {
        return null;
    }
}
