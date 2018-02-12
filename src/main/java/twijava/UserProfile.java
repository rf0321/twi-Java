package twijava;

import twijava.url.TwitterApiURLs;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class UserProfile {

    public String getProfileRequest(String screen_name){

        TreeMap<String,String>param = new TreeMap<>();
        param.put("screen_name",screen_name);

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.requestToAPI("GET", TwitterApiURLs.PROFILE_URL,param);
    }
}
