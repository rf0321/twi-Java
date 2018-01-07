package twijava.api;

/**
 * Request url of TwitterAPI
 *<p>These url is not all, for this Wrapper</p>
 */
public class URLsUtil {

    public static final String END_POINT_URL         = TwitterApiRequestUrls.setUrl("https://api.twitter.com/1.1/");
    public static final String TIMELINE_URL          = TwitterApiRequestUrls.setUrl("statuses/home_timeline.json");
    public static final String USER_TIMELINE_URL     = TwitterApiRequestUrls.setUrl("statuses/user_timeline.json");
    public static final String USER_UPDATESTATUS_URL = TwitterApiRequestUrls.setUrl("statuses/update.json");

    /*
    private static final String SEACH_URL="search/tweets.json";
    private static final String FOLLOWERS_URL="followers/list.json";
    private static final String FOLLOW_URL="friends/list.json";
     */
}
