package twijava.client.requests;

import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterRelativeTweetRequest;

public interface APIRequestInterface {
    // Request instance
    TwitterRelativeTweetRequest twitterTweet();

    TwitterGetTimeLineRequest twitterTimeLine();
}
