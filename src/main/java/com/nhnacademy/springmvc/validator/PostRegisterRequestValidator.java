package com.nhnacademy.springmvc.validator;

import com.nhnacademy.springmvc.domain.PostRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostRegisterRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostRegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "title is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "content is empty");

        PostRegisterRequest request = (PostRegisterRequest) target;
        String content = request.getContent();
        if (content.length() > 100) {
            errors.rejectValue("content", "", "content max length is 100");
        }
    }
}
