package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RetweetController {
    @RequestMapping("/retweet{number}")
    public String printIndex(ModelMap model, @PathVariable("number") int number)
    {
        OperationCenter.retweetOn(number);
        System.out.println("In retCont, id be like "+number);
        return "retweet";
    }

/*
    @RequestMapping("/retweet")
    public String goBack (ModelMap model) {
        System.out.println("In retCont, no id");
        return printIndex(model, -1);
    }
    */
}
