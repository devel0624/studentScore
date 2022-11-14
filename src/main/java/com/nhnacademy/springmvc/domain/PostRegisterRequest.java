package com.nhnacademy.springmvc.domain;

import lombok.Value;

@Value
public class PostRegisterRequest {
    String title;
    String content;
}
