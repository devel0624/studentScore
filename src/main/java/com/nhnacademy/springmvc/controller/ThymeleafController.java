package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/index")
    public String studentIndex(){
        log.info("Thymeleaf Student Index");
        return "thymeleaf/index";
    }

    @GetMapping("/register")
    public String studentRegisterForm(){
        log.info("Thymeleaf Student Register");
        return "thymeleaf/register";
    }

    @GetMapping("/view")
    public String studentViewForm(HttpServletRequest request,
                                  Model model){
        log.info("Thymeleaf Student View");
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        Student student = (Student) redirectMap.get("student");
        model.addAttribute("student",student);

        return "thymeleaf/view";
    }

    @GetMapping("/modify")
    public String studentModifyForm(HttpServletRequest request,
                                    Model model){
        log.info("Thymeleaf Student Modify");
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        Student student = (Student) redirectMap.get("student");
        model.addAttribute("student",student);

        return "thymeleaf/modify";
    }
}

