package com.nhnacademy.springmvc.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
public class StudentRegisterRequest {

    @NotBlank
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @NotBlank
    @Length(max = 200)
    String comment;
}
