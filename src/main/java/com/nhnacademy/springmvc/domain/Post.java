package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

public class Post {
    @Getter
    @Setter
    private long id;

    @Getter
    private final String title;

    @Getter
    private final String content;

    public static Post create(String title, String content) {
        return new Post(title, content);
    }

    private Post (String title, String content) {
        this.title = title;
        this.content = content;
    }

}
