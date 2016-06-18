package com.ashnab.kotoby.web;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RetweetController {

    @RequestMapping("/retweet")
    public String goBack () {
        return "helloTw";
    }

}
