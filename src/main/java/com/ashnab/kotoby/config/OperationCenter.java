package com.ashnab.kotoby.config;

public class OperationCenter {

    private static boolean toRetweet;

    private static int retweetNo;

    public static void retweetInit () {
        toRetweet = false;
        retweetNo = -1;
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

}
