package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieTestController {
    private static final String COOKIE_NAME = "cook2";
    private static final String MORE_PATH = "/cookie-test/more/write";

    @GetMapping("/cookie-test/**/write")
    public String writeCookie(@RequestParam("domain") String domain,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        String path = MORE_PATH.equals(request.getRequestURI()) ? "/cookie-test/more/" : "/";

        Cookie newCookie = new Cookie(COOKIE_NAME, request.getRequestURL().append("?").append(request.getQueryString()).toString());
        newCookie.setDomain(domain);
        newCookie.setPath(path);

        response.addCookie(newCookie);

        return "cookieTest";
    }

    @GetMapping("/cookie-test/**/read")
    @ResponseBody
    public String readCookie(@CookieValue(COOKIE_NAME) String cookieValue) {
        return cookieValue;
    }

    @GetMapping(value = "/cookie-test")
    public String show() {
        return "cookieTest";
    }

}
