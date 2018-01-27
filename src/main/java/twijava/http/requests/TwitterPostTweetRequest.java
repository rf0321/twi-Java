package twijava.http.requests;

import twijava.client.TwitterApiURLs;
import twijava.http.core.HttpRequest;
import twijava.http.HttpRequestInterface;

import java.util.List;
import java.util.TreeMap;

public class TwitterPostTweetRequest implements HttpRequestInterface {

    @Override
    public String getMethod(){

        return "POST";
    }

    @Override
    public HttpRequest http(){

        return new HttpRequest();
    }

    public String updateStatusRequest(String requestUrl, TreeMap<String,String>keyMap, TreeMap<String, String> data) {

        String url = TwitterApiURLs.END_POINT_URL + requestUrl;

        System.out.println("request url:"+url);

        return http().requestToAPI(getMethod(), url, data, keyMap);
    }
}
