package twijava;

import twijava.url.TwitterApiURLs;
import twijava.encode.ParamEncoder;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class Tweet {

    public void tweetRequest(String text){

        TreeMap<String,String> param = new TreeMap<>();
        param.put("status", ParamEncoder.encode(text));
        param.put("trim_user","1");

        HttpRequest httpRequest = new HttpRequest();

        httpRequest.post(TwitterApiURLs.USER_UPDATESTATUS_URL,param);
    }
}
