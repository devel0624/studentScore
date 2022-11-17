package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.LoginRequest;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class LoginController {

    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginForm(){
        log.info("loginForm");
        return "thymeleaf/loginForm";
    }

    @PostMapping ("/login")
    public ModelAndView login(HttpServletResponse response,
                        @ModelAttribute LoginRequest loginRequest,
                        ModelAndView modelAndView){

        String message;

        User user;

        String id = loginRequest.getId();
        String password = loginRequest.getPassword();

        modelAndView.setViewName("thymeleaf/loginForm");

        if(userRepository.exists(id)) {
            user = userRepository.getUser(id);

            if(user.getPassword().equals(password)){
                Cookie cookie = new Cookie("SESSION",user.getId());
                response.addCookie(cookie);

                message = "Welcome, " + user.getId();
                modelAndView.setViewName("redirect:/student");
            }else {
                message = "잘못된 비밀번호 입니다.";
            }
        }else{
            message = "존재하지 않는 아이디입니다.";
        }

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        Cookie cookie = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals("SESSION")).collect(Collectors.toList()).get(0);

        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/login";
    }

}
