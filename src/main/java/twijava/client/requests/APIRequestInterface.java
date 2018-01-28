package twijava.client.requests;

import twijava.http.requests.TwitterGetTimeLineRequest;
import twijava.http.requests.TwitterRelativeTweetRequest;
import twijava.http.requests.TwitterSendDirectMessage;

public interface APIRequestInterface {
    // Request instance
    TwitterRelativeTweetRequest twitterTweet();

    TwitterGetTimeLineRequest twitterTimeLine();

    TwitterSendDirectMessage twitterDirectMessage();
}
