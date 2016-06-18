package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RetweetController {

    @RequestMapping("/retweet")
    public String goBack () {
        System.out.println("IM In retweet cotnrolerq");
        OperationCenter.retweetOn();
        return "retweet";
    }

}
