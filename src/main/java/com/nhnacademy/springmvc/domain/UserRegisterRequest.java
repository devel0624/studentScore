package com.nhnacademy.springmvc.domain;

import lombok.Value;

@Value
public class UserRegisterRequest {
    String id;
    String password;
    int age;

    String name;

}
