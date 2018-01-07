package twijava.api;

import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterPostTweetRequest;

public interface APIRequestComponents {
    // Request instance
    TwitterPostTweetRequest twitterTweet();

    TwitterGetTimeLineRequest twitterTimeLine();
}
