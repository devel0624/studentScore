package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("maskedStudent")
    public Student getMaskedStudent(){
        return Student.maskScoreAndComment();
    }

    public void existStudent(long studentId){
        if (!studentRepository.exists(studentId)){
            throw new StudentNotFoundException();
        }
    }

    @GetMapping
    public String studentIndex(){
        return "index.html";
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable("studentId") long studentId,
                              @RequestParam(name = "hideScore", defaultValue = "no") String upperCase,
                              @RequestParam(name = "hidescore", defaultValue = "no") String lowerCase,
                              ModelMap map){

        existStudent(studentId);

        if (upperCase.equalsIgnoreCase("yes")||lowerCase.equalsIgnoreCase("yes")){
            return "redirect:/student/" + studentId + "/hideScore";
        }

        Student student = studentRepository.getStudent(studentId);

        map.addAttribute("student",student);

        return "/student/view";
    }

    @GetMapping("/{studentId}/hideScore")
    public String hideScoreAndComment(@PathVariable("studentId") long studentId,
                                      @ModelAttribute("maskedStudent") Student maskedStudent,
                                      ModelMap map){

        existStudent(studentId);

        Student student = studentRepository.getStudent(studentId);

        maskedStudent.setName(student.getName());
        maskedStudent.setEmail(student.getEmail());

        map.addAttribute("student",maskedStudent);

        return "/student/view";
    }


    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable("studentId") long studentId,
                                    Model model) {
        existStudent(studentId);

        Student student = studentRepository.getStudent(studentId);

        model.addAttribute("student",student);

        return "modify.html";
    }

    @PostMapping("/{studentId}/modify")
    public ModelAndView modifyStudent(@PathVariable("studentId") long studentId,
                                      @Valid @ModelAttribute StudentModifyRequest request) {

        Student student = studentRepository.modify(studentId,request);

        ModelAndView mav = new ModelAndView("view.html");

        mav.addObject("student",student);

        return mav;
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student Data Not Found")
    public void notFound() {
        // TODO document why this method is empty
    }

}
