package com.nhnacademy.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.config.RootConfig;
import com.nhnacademy.springmvc.config.WebConfig;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.repository.StudentRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = { RootConfig.class }),
        @ContextConfiguration(classes = WebConfig.class)
})
class StudentRestControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;
    private Student student;
    String jsonStr;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        long id = 1;
        String name = "김아무개";
        String email = "test@aaa.com";
        int score = 100;
        String comment = "테스트";

        student = Student.create(id,name,email,score,comment);

        StudentRegisterRequest request = new StudentRegisterRequest();

        request.setName(student.getName());
        request.setEmail(student.getEmail());
        request.setScore(student.getScore());
        request.setComment(student.getComment());

        ObjectMapper objectMapper = new ObjectMapper();

        jsonStr = objectMapper.writeValueAsString(request);

        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();

    }

    @Test
    void getStudent() throws Exception {

        MvcResult result = mockMvc.perform(get("/students/{studentId}",student.getId())
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isFound())
                .andDo(print())
                 .andReturn();

        HttpServletResponse response = result.getResponse();

        System.out.println(response);
    }

    @Test
    void getStudentFail() throws Exception {

        Throwable th = catchThrowable(() -> mockMvc.perform(get("/students/{studentId}",2))
                .andDo(print()));

        assertThat(th).isInstanceOf(StudentNotFoundException.class);

    }

    @Test
    void postStudentSuccess() throws Exception {
        log.info(jsonStr);

        MvcResult result = mockMvc.perform(post("/students/")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        HttpServletResponse response = result.getResponse();

        System.out.println(response);
    }

    @Test
    void putStudent() {

        Throwable th = catchThrowable(() ->
                mockMvc.perform(put("/students/{studentId}",student.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)));

    }
}