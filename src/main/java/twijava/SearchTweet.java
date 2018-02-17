package twijava;

import twijava.url.TwitterApiURLs;
import twijava.encode.ParamEncoder;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class SearchTweet {

    public String searchRequest(String query){

        TreeMap<String, String> param = new TreeMap<>();
        param.put("q", ParamEncoder.encode(query));

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.get(TwitterApiURLs.SEACH_URL,param);
    }
}
