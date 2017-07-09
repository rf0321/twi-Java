package TwiJava.forJson;

public class TwitterJsonObjects //適当に拾ってきたObjects
{
  public class Tweet {
    public long id;
    public String id_str;
    public String created_at;
    public String text;

    public String in_reply_to_screen_name;
    public int in_reply_to_status_id;
    public String in_reply_to_status_id_str;
    public int in_reply_to_user_id;
    public String in_reply_to_user_id_str;
    public boolean retweeted;
    public int retweet_count;
    public boolean favorited;
    public int favorite_count;
    public int quoted_status_id;
    public String quoted_status_id_str;

    public TweetUser user;
    public Entities entities;
    public Extended_Entities extended_entities;

    public String lang;
  }

  public class TweetUser {
    public int id;
    public String id_str;
    public String name;
    public String screen_name;
    public String location;
    public String description;
    public String url;
    public boolean verified;

    public String profile_text_color;
    public boolean profile_user_background_image;
    public String profile_background_image_url;
    public String profile_background_image_url_https;
    public String profile_background_color;
    public String profile_banner_url;
    public boolean profile_background_tile;
    public String profile_image_url;

    public int statuses_count;
    public int friends_count;
    public int followers_count;
    public int favourites_count;
    public boolean following;
    public boolean follow_request_sent;
    public int listed_count;

//		public Tweet status;

  }


  public class Retweet {
    public long id;
    public String id_str;
    public String created_at;
    public String text;

    public String in_reply_to_screen_name;
    public int in_reply_to_status_id;
    public String in_reply_to_status_id_str;
    public int in_reply_to_user_id;
    public String in_reply_to_user_id_str;
    public boolean retweeted;
    public int retweet_count;
    public String retweeted_status = "";
    public boolean favorited;
    public int favorite_count;
    public int quoted_status_id;
    public String quoted_status_id_str;

    public TweetUser user;
  }

  // ==============================
  // Entities Class
  // ==============================

  public class Entities {
    public Media[] media;
    public UserMention[] user_mentions;
    public HashTag[] hashtags;
  }
  public class Extended_Entities
  {
    public Media[] media;
  }
  public class Media
  {
    public int id;
    public int id_str;
    public String media_url;
    public String media_url_https;
    public String type;
    public Video_Info video_info;
  }
  public class UserMention
  {
    public int id;
    public String id_str;
    public String screen_name;
    public String name;
  }
  public class HashTag
  {
    public String text;
  }
  public class Symbol
  {
    public String text;
  }
  public class Video_Info
  {
    public long id;
    public String id_str;
    public String media_url;
    public String type;
    public Variant[] variants;
  }
  public class Variant
  {
    public int bitrate;
    public String content_type;
    public String url;
  }
  // ==============================
  // Response Class
  // ==============================

  public class SearchTweetsResponse {
    public Tweet[] statuses;
  }
  public class StatusesUserTimelineResponse {
    public Tweet[] items;
  }

  public class StatusesHomeTimelineResponse {
    public Tweet[] items;
  }


  public class FollowersListResponse {
    public TweetUser[] users;
  }

  public class FriendsListResponse {
    public TweetUser[] users;
  }
  public class Tweets {
    public Tweet[] items;
  }

}


