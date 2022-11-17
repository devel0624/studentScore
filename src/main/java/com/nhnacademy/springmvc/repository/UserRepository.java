package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.User;

public interface UserRepository {
    boolean exists(String id);
    User getUser(String id);
    void register(String admin, String password);
}
