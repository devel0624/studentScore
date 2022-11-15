package com.nhnacademy.springmvc.domain;

import lombok.Value;

@Value
public class StudentRegisterRequest {
    String name;
    String email;
    int score;
    String comment;
}
