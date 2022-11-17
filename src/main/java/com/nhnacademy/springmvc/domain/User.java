package com.nhnacademy.springmvc.domain;

import lombok.Getter;

public class User {
    @Getter
    final String id;

    @Getter
    final String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
