package twijava.client.requests;

import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterPostTweetRequest;

public interface APIRequestInterface {
    // Request instance
    TwitterPostTweetRequest twitterTweet();

    TwitterGetTimeLineRequest twitterTimeLine();
}
