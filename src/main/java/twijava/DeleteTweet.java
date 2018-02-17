package twijava;

import twijava.url.TwitterApiURLs;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class DeleteTweet {

    public void deleteRequest(String idStr){

        TreeMap<String,String> param = new TreeMap<>();
        param.put("id",idStr);

        HttpRequest httpRequest = new HttpRequest();

        String requestUri = TwitterApiURLs.USER_DESTROY_URL+idStr+".json";

        httpRequest.post(requestUri, param);
    }
}
