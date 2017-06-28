package com.company;

//Using Example of this library
import java.io.IOException;

public class Main { //実装する理想

    public static void main(String[] args) throws IOException {
        TwiJava updateJson = new TwiJava("ConsumerKey", "ConsumerSecret","AccessToken", "AccessTokenSecret");
        System.out.println(updateJson.tweet("Hello World"));
        System.out.println(updateJson.gethomeTimeline());
    }
}
