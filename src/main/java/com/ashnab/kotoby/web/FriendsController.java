package com.ashnab.kotoby.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendsController {

    @RequestMapping("/connect/friends")
    public String friends() {
        return "/connect/friends";
    }

}
