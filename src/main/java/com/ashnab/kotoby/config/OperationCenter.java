package com.ashnab.kotoby.config;

public class OperationCenter {

    private static int retweet;

    public static void retweetInit () {
        retweet = 0;
    }

    public static void retweetOn () {
        retweet = 1;
    }

    public static void retweetOff () {
        retweet = 0;
    }

    public static int toRetweet () {
        return retweet;
    }

}
