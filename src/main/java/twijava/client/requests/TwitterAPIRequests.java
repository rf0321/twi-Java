package twijava.client.requests;

import twijava.client.TwitterApiURLs;
import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterPostTweetRequest;


import java.util.TreeMap;

public class TwitterAPIRequests implements APIRequestInterface {

    @Override
    public TwitterGetTimeLineRequest twitterTimeLine(){
        return new TwitterGetTimeLineRequest();
    }

    @Override
    public TwitterPostTweetRequest twitterTweet(){
        return new TwitterPostTweetRequest();
    }

    public String updateStatus(TreeMap<String,String> dataForPost, TreeMap<String,String>keyMap){

        return twitterTweet().updateStatusRequest(TwitterApiURLs.USER_UPDATESTATUS_URL,keyMap,dataForPost);
    }

    public String homeStatus(TreeMap<String,String> dataForGet, TreeMap<String,String>keyMap) {

        return twitterTimeLine().getStatusRequest(TwitterApiURLs.HOME_TIMELINE_URL,keyMap,dataForGet);
    }

    public String userStatus(TreeMap<String,String> dataForGet, TreeMap<String,String>keyMap){

        return twitterTimeLine().getStatusRequest(TwitterApiURLs.USER_TIMELINE_URL,keyMap,dataForGet);
    }

    public String searchStatus(TreeMap<String,String>dataForGet,TreeMap<String,String>keyMap){

        return twitterTimeLine().getStatusRequest(TwitterApiURLs.SEACH_URL,keyMap,dataForGet);
    }
}