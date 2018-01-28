package twijava.http.requests;

import twijava.client.TwitterApiURLs;
import twijava.http.HttpRequestInterface;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class TwitterSendDirectMessage implements HttpRequestInterface {

    @Override
    public String getMethod(){

        return "POST";
    }

    @Override
    public HttpRequest http(){

        return new HttpRequest();
    }

    public String sendDirectMessageRequest(String requestUrl, TreeMap<String,String> keyMap, TreeMap<String, String> data) {

        String url = TwitterApiURLs.END_POINT_URL + requestUrl;

        System.out.println("request url:"+ url);

        return http().requestToAPI(getMethod(), url, data, keyMap);
    }
}
