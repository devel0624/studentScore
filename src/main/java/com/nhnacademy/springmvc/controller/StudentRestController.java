package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping( value = "/students"
)
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") long id){

        if (!studentRepository.exists(id)){
            throw new StudentNotFoundException();
        }

        Student student = studentRepository.getStudent(id);

        return student;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> postStudent(@Valid  @RequestBody StudentRegisterRequest request,
                                      BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        log.info(request.getName());

        Student student = studentRepository.register(request.getName(), request.getEmail(), request.getScore(), request.getComment());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public Student putStudent(@PathVariable("studentId") long id,
                                              @Valid @RequestBody StudentModifyRequest request,
                                              BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if (!studentRepository.exists(id)){
            throw new StudentNotFoundException();
        }


        Student student = studentRepository.modify(id,request);

        return student;
    }
}
