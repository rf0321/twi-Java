package twijava;

import twijava.url.TwitterApiURLs;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class UserProfile {

    public String getProfileRequest(String screenName){

        TreeMap<String,String>param = new TreeMap<>();
        param.put("screen_name",screenName);

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.get(TwitterApiURLs.PROFILE_URL,param);
    }
}
