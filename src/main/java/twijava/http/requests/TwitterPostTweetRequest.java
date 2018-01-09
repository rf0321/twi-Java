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

    public String updateStatusRequest(String requestUrl, List<String> keylist, TreeMap<String, String> data) {

        String url = TwitterApiURLs.END_POINT_URL + requestUrl;

        return http().requestToAPI(getMethod(), url, data, keylist);
    }
}
