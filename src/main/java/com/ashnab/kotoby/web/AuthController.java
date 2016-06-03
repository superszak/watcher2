package com.ashnab.kotoby.web;

import org.hibernate.annotations.SourceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @RequestMapping("/auth/twitter")
    public String twitter() {
        return "/connect/twitterConnected";
    }

    @RequestMapping("*")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        System.out.println("\n\n\n!!!\nHELO MOT\n!!!\n\n\n");
        return "connect/twitterConnected";
    }

}
