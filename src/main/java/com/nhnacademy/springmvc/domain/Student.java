package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

public class Student {

    @Getter
    private long id;
    @Setter
    @Getter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private int score;
    @Getter
    @Setter
    private String comment;

    private Student(long id, String name, String email, int score, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public static Student create(long id, String name, String email, int score, String comment){
        return new Student(id, name, email, score, comment);
    }

    private static String COMMENT_MASK = "*****";
    private static int SCORE_MASK = 0;
    public static Student maskScoreAndComment() {
        return new Student(0,"김아무개","aaa@aaa.com", SCORE_MASK, COMMENT_MASK);
    }
}
