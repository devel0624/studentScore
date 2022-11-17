package com.nhnacademy.springmvc.domain;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class LoginRequest {

    @NotBlank
    String id;

    @NotBlank
    String password;
}
