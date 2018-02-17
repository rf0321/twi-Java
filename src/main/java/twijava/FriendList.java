package twijava;

import twijava.http.core.HttpRequest;
import twijava.url.TwitterApiURLs;

import java.util.TreeMap;

public class FriendList {

    public String getFriendRequest(){

        TreeMap<String,String> param = new TreeMap<>();
        param.put("cursor","-1");

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.get(TwitterApiURLs.FRIENDS_URL,param);
    }
}
