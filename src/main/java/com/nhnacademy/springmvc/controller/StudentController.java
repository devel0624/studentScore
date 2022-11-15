package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") long studentId){

        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException();
        }
        
        return studentRepository.getStudent(studentId);
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@RequestParam(name = "hidescore", defaultValue = "no") String hideScore,
                              @ModelAttribute Student student,
                              Model model) {
        if(hideScore.equalsIgnoreCase("yes")){
            model.addAttribute("student",Student.maskScoreAndComment(student));
        }
        return "/student/view";
    }


    @GetMapping("/{studentId}/modify")
    public String studentModifyForm() {
        return "/student/modify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@ModelAttribute StudentModifyRequest request,
                             @ModelAttribute Student student,
                             Model model) {

        student.setEmail(request.getEmail());
        student.setScore(request.getScore());
        student.setComment(request.getComment());

        studentRepository.modify(student);

        model.addAttribute(student);

        return "/student/view";
    }

}
