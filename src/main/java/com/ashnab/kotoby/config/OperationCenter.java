package com.ashnab.kotoby.config;

public class OperationCenter {

    private static boolean toRetweet;
    private static int retweetNo;

    private static boolean toLike;
    private static int likeNo;

    public static void retweetInit () {
        toRetweet = false;
        retweetNo = -1;
        toLike = false;
        likeNo = -1;
    }

    public static void retweetOn (int no) {
        if (no >= 0) {
            toRetweet = true;
            retweetNo = no;
        }
    }

    public static void retweetOff () {
        toRetweet = false;
        retweetNo = -1;
    }

    public static boolean toRetweet () {
        return toRetweet;
    }

    public static int retweetNo() {
        return retweetNo;
    }

    public static void likeOn (int no) {
        if (no >= 0) {
            toLike = true;
            likeNo = no;
        }
    }

    public static void likeOff () {
        toLike = false;
        likeNo = -1;
    }

    public static boolean toLike () {
        return toLike;
    }

    public static int likeNo () {
        return likeNo;
    }
}
