package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.social.connect.ConnectionRepository;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("helloTw")
public class TwitterController {

    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        model.addAttribute(twitter.userOperations().getUserProfile());

        if (OperationCenter.toRetweet() == 1) {
            List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
            twitter.timelineOperations().retweet(tweets.get(9).getId());
            System.out.println("retweeting");

            OperationCenter.retweetOff();
        }
        /*
        CursoredList<TwitterProfile> followers = twitter.friendOperations().getFollowers();
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();

        model.addAttribute("friends", friends);
        model.addAttribute("followers", followers);
        model.addAttribute("posts", tweets);
        System.out.println("tweeting");
        //twitter.timelineOperations().updateStatus("Spring Social is awesome!");
        //return "connect/twitterConnected";
        */
        System.out.println("IMA IN twiter controler");
        return "helloTw";
    }

    @RequestMapping(value="retweet", method=RequestMethod.GET)
    public ModelAndView str() {
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        twitter.timelineOperations().retweet(tweets.get(9).getId());
        System.out.println("retweeting");
        return new ModelAndView("retweet");
    }

}
