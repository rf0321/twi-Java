package twijava;

import twijava.http.core.HttpRequest;
import twijava.url.TwitterApiURLs;

import java.util.TreeMap;

public class FollowerList {

    public String getFollowerRequest(){

        TreeMap<String,String> param = new TreeMap<>();
        param.put("cursor","-1");

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.get(TwitterApiURLs.FOLLOWERS_URL,param);
    }
}
