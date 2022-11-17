package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRequestBody;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/{userId}")
    public ResponseEntity<String> getStudent(@PathVariable("userId") long id){

        log.info(String.valueOf(id));
        Student student = studentRepository.getStudent(id);

        log.info(student.getName());

        JSONObject json = new JSONObject();

        json.put("name",student.getName());
        json.put("email",student.getEmail());
        json.put("score",student.getScore());
        json.put("comment",student.getComment());

        return ResponseEntity.ok().body(json.toString());
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> add(@RequestBody StudentRequestBody request,
                                       BindingResult bindingResult) {

        log.info(request.getName());

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }



        Student student = studentRepository.register(
                request.getName(),
                request.getEmail(),
                Integer.parseInt(request.getScore()),
                request.getComment()
        );

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PutMapping
    public void modify(@RequestBody Student student) {
        // ...
    }
}
