package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.domain.UserModifyRequest;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User getUser(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@ModelAttribute User user, Model model) {
        model.addAttribute("user", User.constructPasswordMaskedUser(user));
        return "userInfo";
    }

    @GetMapping("/{userId}/modify")
    public String userModifyForm(@ModelAttribute User user, Model model) {
        model.addAttribute("user", User.constructPasswordMaskedUser(user));
        return "userModify";
    }

    @PostMapping("/{userId}/modify")
    public String modifyUser(@ModelAttribute User user,                                // 기존 user 정보
                             @Valid @ModelAttribute UserModifyRequest userRequest,     // 수정 요청 객체
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        userRepository.modify(user);

        model.addAttribute("user", user);
        return "userInfo";
    }

}
