package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeartController {
    @RequestMapping("/twlike{number}")
    public String printIndex(ModelMap model, @PathVariable("number") int number) {
        OperationCenter.likeOn(number);
        //System.out.println("In retCont, id be like " + number);
        return "twlike";
    }
}