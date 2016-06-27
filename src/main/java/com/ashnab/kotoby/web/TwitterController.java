package com.ashnab.kotoby.web;

import com.ashnab.kotoby.config.OperationCenter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
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
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        model.addAttribute("posts", tweets);

        if (OperationCenter.toRetweet()) {
            twitter.timelineOperations().retweet(tweets.get(OperationCenter.retweetNo()).getId());
            System.out.println("retweeting");
            OperationCenter.retweetOff();
        }

        if (OperationCenter.toLike()) {
            twitter.timelineOperations().addToFavorites(tweets.get(OperationCenter.likeNo()).getId());
            OperationCenter.likeOff();
        }

        return "helloTw";
    }
}
