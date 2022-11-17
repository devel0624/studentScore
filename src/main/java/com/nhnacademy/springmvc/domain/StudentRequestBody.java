package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestBody {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String score;

    @Getter
    @Setter
    String comment;
}
