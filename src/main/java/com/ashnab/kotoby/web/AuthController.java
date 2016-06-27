package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
@RequestMapping(value = {"/", "signup#_=_", "signup"})
//@RequestMapping("/")
public class AuthController {

    private Facebook facebook;
    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public AuthController(Facebook facebook, Twitter twitter, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView hello(Model model) {

        boolean hasFb;
        boolean hasTwitter;

        OperationCenter.retweetInit();

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            hasFb = false;
        } else {
            hasFb = true;
        }
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            hasTwitter = false;
        } else {
            hasTwitter = true;
        }
        System.out.println(hasFb);
        if (hasTwitter && hasFb) {
            return new ModelAndView("redirect:/helloAll");
        } else if (hasTwitter) {
            return new ModelAndView("redirect:/helloTw");
        } else if (hasFb) {
            return new ModelAndView("redirect:/helloFb");
        } else {
            return new ModelAndView("redirect:/helloNothing");
        }

    }
}
