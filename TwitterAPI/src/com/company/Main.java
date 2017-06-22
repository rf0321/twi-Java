package com.company;

public class Main { //実装する理想

    public static void main(String[] args) {
        TweetInterface twin = new TweetMessage("Hello World!!");
        TwitterAPI twitter = new TwitterAPI("", "", "", "");
        twitter.Tweet(twin.sentence());
    }
}
