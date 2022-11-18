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
@RequestMapping("/students")
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") long id){

        if (!studentRepository.exists(id)){
            throw new StudentNotFoundException();
        }

        Student student = studentRepository.getStudent(id);

        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> add(@Valid  @RequestBody StudentRegisterRequest request,
                                      BindingResult bindingResult) {

        log.info(request.getName());

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.register(request.getName(), request.getEmail(), request.getScore(), request.getComment());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> modify(@PathVariable("studentId") long id,
                                          @Valid @RequestBody StudentModifyRequest request) {

        if (!studentRepository.exists(id)){
            throw new StudentNotFoundException();
        }

        Student student = studentRepository.modify(id,request);

        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }
}
