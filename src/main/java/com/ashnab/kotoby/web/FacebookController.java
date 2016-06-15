package com.ashnab.kotoby.web;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
//@RequestMapping("/")
@RequestMapping("/")
public class FacebookController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Inject
    public FacebookController (Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook (Model model) {
        boolean hasFb;
        boolean hasTwitter;

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

        if (hasFb) {
            model.addAttribute(facebook.userOperations().getUserProfile());
            List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
            model.addAttribute("friends", friends);
            facebook.feedOperations().updateStatus("HELO WORDL");
            return "hello2";
        } else {
            return "redirect:login";
        }
    }

}
