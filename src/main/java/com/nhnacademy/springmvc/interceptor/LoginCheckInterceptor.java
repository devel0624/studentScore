package com.nhnacademy.springmvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Cookie[] cookie = request.getCookies();

            if(Objects.isNull(cookie)){
                throw new NullPointerException("Cookie is Null");
            }

            if(Arrays.stream(cookie).noneMatch(x -> x.getName().equals("SESSION"))){
                response.sendRedirect("/login");
                return false;
            }

        }catch (NullPointerException e){
            log.info("",e);
            response.sendRedirect("/login");
        }

        return true;
    }
}

