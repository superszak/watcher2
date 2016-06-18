package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/")
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

        System.out.println("IM NOW TRYNA TO GET REDIRECTED");

        OperationCenter.retweetInit();

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            hasFb = false;
        } else {
            hasFb = true;
        }
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            //return "redirect:/connect/twitter";
            hasTwitter = false;
        } else {
            hasTwitter = true;
        }

        if (hasTwitter && hasFb) {
           // getTwitterContent(model, twitter);
            //getFacebookContent(model, facebook);
            return new ModelAndView("redirect:/helloAll");
        } else if (hasTwitter) {
            //getTwitterContent(model, twitter);
            return new ModelAndView("redirect:/helloTw");
        } else {
            //getFacebookContent(model, facebook);
            return new ModelAndView("redirect:/helloFb");
        }

    }
}
