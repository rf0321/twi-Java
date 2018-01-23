package twijava.http.requests;

import twijava.client.TwitterApiURLs;
import twijava.http.core.HttpRequest;
import twijava.http.HttpRequestInterface;

import java.util.List;
import java.util.TreeMap;

public class TwitterGetTimeLineRequest implements HttpRequestInterface {

    @Override
    public String getMethod(){

        return "GET";
    }

    @Override
    public HttpRequest http(){

        return new HttpRequest();
    }

    public String getStatusRequest(String requestUrl, TreeMap<String,String>keyMap, TreeMap<String,String> dataForGet){

        String url = TwitterApiURLs.END_POINT_URL + requestUrl;

        return http().requestToAPI(getMethod(), url, dataForGet, keyMap);
    }
}
