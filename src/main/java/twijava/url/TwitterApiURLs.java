package twijava.url;

/**
 * Request url of TwitterAPI
 *<p>These url is not all, for this Wrapper</p>
 */
public class TwitterApiURLs {

    public static final String END_POINT_URL         = ("https://api.twitter.com/1.1/");

    public static final String HOME_TIMELINE_URL     = ("statuses/home_timeline.json");

    public static final String USER_TIMELINE_URL     = ("statuses/user_timeline.json");

    public static final String USER_UPDATESTATUS_URL = ("statuses/update.json");

    public static final String USER_DESTROY_URL      = ("statuses/destroy/");

    public static final String SEACH_URL             = ("search/tweets.json");

    public static final String PROFILE_URL           = ("users/show.json");

    public static final String FOLLOWERS_URL         = ("followers/list.json");

    public static final String FRIENDS_URL           = ("friends/list.json");

    public static final String USER_CREATEMESSAGE_URL= ("direct_messages/events/new.json");

    /*
    private static final String FOLLOWERS_URL="followers/list.json";
    private static final String FOLLOW_URL="friends/list.json";
     */
}
