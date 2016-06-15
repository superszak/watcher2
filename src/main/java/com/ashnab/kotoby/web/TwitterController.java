package com.ashnab.kotoby.web;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
//@RequestMapping("/")
public class TwitterController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String helloTwitter(Model model) {

        boolean hasFb;
        boolean hasTwitter;

        //System.out.println("IM NOW TRYNA TO GET REDIRECTED");

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

        if (hasTwitter) {
            model.addAttribute(twitter.userOperations().getUserProfile());
            CursoredList<TwitterProfile> followers = twitter.friendOperations().getFollowers();
            CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
            List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
            model.addAttribute("friends", friends);
            model.addAttribute("followers", followers);
            model.addAttribute("posts", tweets);
            System.out.println("tweeting");
            //twitter.timelineOperations().updateStatus("Spring Social is awesome!");
            //return "connect/twitterConnected";
            return "hello";
        } else {
            //return "redirect:login";
            return "hello";
        }
    }
}