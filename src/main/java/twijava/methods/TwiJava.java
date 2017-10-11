package twijava.methods;
import twijava.json.util.JsonDecoder;
import twijava.net.ParamSupporter;
import twijava.net.http.HttpRequest;

import java.util.TreeMap;

/**
 * 認証にいるキー
 *  consumerKey
 *  consumerSecretKey
 *  accessToken
 *  accessTokenSecret
 */
public class TwiJava{ //Method components class of this library.

    private String consumerKey;
    private String consumersecretKey;
    private String accessToken;
    private String accessTokenSecret;

    private static final String TIMELINE_URL="statuses/home_timeline.json";
    private static final String USER_TIMELINE_URL="statuses/user_timeline.json";
    private static final String USER_UPDATESTATUS_URL="statuses/update.json";

    private static final String SEACH_URL="search/tweets.json";
    private static final String FOLLOWERS_URL="followers/list.json";
    private static final String FOLLOW_URL="friends/list.json";



    public static class TokenInitialize  { //APIトークンの設定クラス

        private String ck,cks,at,ats;

        public TokenInitialize setConsumerKey(String key){
            ck=key;
            return this;
        }
        public TokenInitialize setConsumerSecretKey(String key){
            cks=key;
            return this;
        }
        public TokenInitialize setAccessToken(String key){
            at=key;
            return this;
        }
        public TokenInitialize setAccessTokenSecret(String key){
            ats=key;
            return this;
        }
        public TwiJava buildTokens(){
            return new TwiJava(ck,cks,at,ats);
        }
     }
     private TwiJava(String consumerKey,String consumersecretKey,
                    String accessToken,String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumersecretKey = consumersecretKey;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
     }
    public void tweet(String text){
        TreeMap<String,String>data=new TreeMap<>();
        data.put("status", ParamSupporter.urlEncode(text));
        data.put("trim_user","1");

        if(text.length()>140) {
            System.out.println("You cant this tweet because the content charactor over 140");
            System.exit(0);
        }
        try {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.request("POST", USER_UPDATESTATUS_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getHomeTimeLine(Integer counter){
        JsonDecoder.timelinedecode(homeTimeLineJson(counter));
    }

    public void getUserTimeLine(Integer counter){
        JsonDecoder.timelinedecode(userTimeLineJson(counter));
    }

    public void getSearchTweetJson(String word){
        System.out.println(searchTweetJson(word));
    }

    public void getUserFollowerListJson(){
        System.out.println(userFollowerListJson());
    }

    public void getUserFollowListJson(){
        System.out.println(userFollowListJson());
    }

    private String searchTweetJson(String searchWord){
        TreeMap<String,String>data=new TreeMap<>();
        data.put("q",searchWord);
        try {
            HttpRequest httpRequest = new HttpRequest();
            return httpRequest.request("GET",SEACH_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, data);
        }
        catch (Exception e){
            return "";
        }
    }

    private String userFollowerListJson(){
        TreeMap<String, String> followerdata = new TreeMap<>();
        followerdata.put("cursor","-1");
        try{
            HttpRequest httpRequest = new HttpRequest();

           return httpRequest.request("GET", FOLLOWERS_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, followerdata);
        }
        catch (Exception e){
            return "";
        }
    }
    private String userFollowListJson(){
        TreeMap<String, String> followdata = new TreeMap<>();
        followdata.put("cursor","-1");
        try{
            HttpRequest httpRequest = new HttpRequest();

            return httpRequest.request("GET", FOLLOW_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, followdata);
        }
        catch (Exception e){
            return "";
        }
    }

    public String homeTimeLineJson(Integer counter){
        if(counter>200){
            System.out.println("Cant get timeline tweets over 200");
            System.exit(0);
        }
           TreeMap<String, String> hometimelineData = new TreeMap<>();
           hometimelineData.put("count", counter.toString());
           hometimelineData.put("trim_user", "1");
       try{
           HttpRequest httpRequest = new HttpRequest();

           return httpRequest.request("GET", TIMELINE_URL, consumerKey, accessToken,
                   consumersecretKey, accessTokenSecret, hometimelineData);
       }
       catch (Exception e){
           return "";
       }
    }

    public String userTimeLineJson(Integer counter){
        if(counter>200){
            System.out.println("Cant get timeline tweets over 200");
            System.exit(0);
        }
        TreeMap<String,String>usertimelineData=new TreeMap<>();
        usertimelineData.put("count",counter.toString());
        usertimelineData.put("trim_user","1");
        try {
            HttpRequest httpRequest = new HttpRequest();
            return httpRequest.request("GET", USER_TIMELINE_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, usertimelineData);
        }
        catch (Exception e){
            return "";
        }
    }
}
