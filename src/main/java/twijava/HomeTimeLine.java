package twijava;

import twijava.url.TwitterApiURLs;
import twijava.http.core.HttpRequest;

import java.util.TreeMap;

public class HomeTimeLine {

    public String getTimeLineRequest(int count){

        String sendCount = String.valueOf(count);

        TreeMap<String, String> param = new TreeMap<>();
        param.put("count", sendCount);
        param.put("trim_user", "1");

        HttpRequest httpRequest = new HttpRequest();

        return httpRequest.requestToAPI("GET", TwitterApiURLs.HOME_TIMELINE_URL,param);
    }
}
