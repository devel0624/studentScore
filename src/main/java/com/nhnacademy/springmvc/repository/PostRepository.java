package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Post;

public interface PostRepository {
    boolean exists(long id);

    Post register(String title, String content);

    Post getPost(long id);

}
