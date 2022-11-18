package com.nhnacademy.springmvc.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class StudentModifyRequest {

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
