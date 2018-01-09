package twijava.client.requests;

import twijava.client.TwitterApiURLs;
import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterPostTweetRequest;


import java.util.List;
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

    public String updateStatus(TreeMap<String,String> dataForPost, List<String>keylist){

        return twitterTweet().updateStatusRequest(TwitterApiURLs.USER_UPDATESTATUS_URL,keylist,dataForPost);
    }

    public String homeStatus(TreeMap<String,String> dataForGet, List<String>keylist) {

        return twitterTimeLine().getStatusRequest(TwitterApiURLs.HOME_TIMELINE_URL, keylist,dataForGet);
    }

    public String userStatus(TreeMap<String,String> dataForGet, List<String>keylist){

        return twitterTimeLine().getStatusRequest(TwitterApiURLs.USER_TIMELINE_URL, keylist,dataForGet);
    }
}