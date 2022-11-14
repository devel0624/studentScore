package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Post;
import com.nhnacademy.springmvc.exception.PostNotFoundException;
import com.nhnacademy.springmvc.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @ModelAttribute("post")
    public Post getPost(@PathVariable("postId") long postId) {
        return postRepository.getPost(postId);
    }

    @GetMapping("/{postId}")
    public String viewPost(@ModelAttribute("post") Post post,
                           @PathVariable("postId") long postId,
                           ModelMap modelMap) {
        if (Objects.isNull(post)) {
            modelMap.put("exception", new PostNotFoundException());
            return "error";
        }

        modelMap.put("post", post);
        return "postView";
    }

    @GetMapping("/{postId}/modify")
    public String postModifyForm(@ModelAttribute("post") Post post,
                                 Model model) {
        if (Objects.isNull(post)) {
            model.addAttribute("exception", new PostNotFoundException());
            return "error";
        }

        model.addAttribute("post", post);
        return "postModify";
    }
}
