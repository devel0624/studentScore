package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.User;

import java.util.HashMap;
import java.util.Map;

public class AdminRepository implements UserRepository{

    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return users.containsKey(id);
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public void register(String id, String password) {
        User user = new User(id,password);
        users.put(id,user);
    }
}
