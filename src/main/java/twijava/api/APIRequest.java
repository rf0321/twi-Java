package twijava.api;

import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterPostTweetRequest;

import java.util.List;
import java.util.TreeMap;

public class APIRequest implements APIRequestComponents{

    @Override
    public TwitterPostTweetRequest twitterTweet(){
        return new TwitterPostTweetRequest();
    }

    @Override
    public TwitterGetTimeLineRequest twitterTimeLine(){
        return new TwitterGetTimeLineRequest();
    }

    public String updateStatus(TreeMap<String,String> postData, List<String>keylist){

        return twitterTweet().updateStatusRequest(URLsUtil.USER_UPDATESTATUS_URL,keylist,postData);
    }

    public String showHomeStatus(TreeMap<String,String> showData, List<String>keylist) {

        return twitterTimeLine().showStatusRequest(URLsUtil.TIMELINE_URL, keylist,showData);
    }

    public String showUserStatus(TreeMap<String,String>showData, List<String>keylist){

        return twitterTimeLine().showStatusRequest(URLsUtil.USER_TIMELINE_URL, keylist, showData);
    }
}