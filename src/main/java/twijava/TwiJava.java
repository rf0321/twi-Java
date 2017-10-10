package twijava;
import twijava.json.util.JsonDecoder;
import twijava.net.ParamSupporter;
import twijava.net.core.HttpRequest;
import java.util.TreeMap;

/**
 * 認証にいるキー
 *  consumerKey
 *  consumerSecretKey
 *  accessToken
 *  accessTokenSecret
 */
public class TwiJava{ //Method components of this library.

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



    public static class SetAPIToken  { //APIトークンの設定クラス

        private String ck,cks,at,ats;

        public SetAPIToken setConsumerKey(String key){
            ck=key;
            return this;
        }
        public SetAPIToken setConsumerSecretKey(String key){
            cks=key;
            return this;
        }
        public SetAPIToken setAccessToken(String key){
            at=key;
            return this;
        }
        public SetAPIToken setAccessTokenSecret(String key){
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
        data.put("status", ParamSupporter.urlEncode(text)); //明日urlencodeしないでためす。もしくはUS-ASCIIでエンコードしてみる
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
        try {
            String result=homeTimeLineJson(counter);
            System.out.println("------Parsed json data------");
            JsonDecoder.decode(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getUserTimeLine(Integer counter){
        try {
            String result=userTimeLineJson(counter);
            System.out.println("------Parsed json data------");
            JsonDecoder.decode(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void searchTweetWord(String searchWord){
        TreeMap<String,String>data=new TreeMap<>();
        data.put("q",searchWord);
        try {
            HttpRequest httpRequest = new HttpRequest();
            String resultjson = httpRequest.request("GET",SEACH_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, data);
            System.out.print(resultjson);
        }
        catch (Exception e){
        }
    }

    public void getUserFollowerList(){
        TreeMap<String, String> followerdata = new TreeMap<>();
        followerdata.put("cursor","-1");
        try{
            HttpRequest httpRequest = new HttpRequest();

           String result=httpRequest.request("GET", FOLLOWERS_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, followerdata);
           System.out.println(result);
        }
        catch (Exception e){
        }
    }
    public void getUserFollowList(){
        TreeMap<String, String> followdata = new TreeMap<>();
        followdata.put("cursor","-1");
        try{
            HttpRequest httpRequest = new HttpRequest();

            String result=httpRequest.request("GET", FOLLOW_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, followdata);
            System.out.println(result);
        }
        catch (Exception e){
        }
    }

    public String homeTimeLineJson(Integer counter){
           TreeMap<String, String> hometimelineData = new TreeMap<>();
           hometimelineData.put("count", counter.toString());
           hometimelineData.put("trim_user", "1");
       try{
           HttpRequest httpRequest = new HttpRequest();

           return httpRequest.request("GET", TIMELINE_URL, consumerKey, accessToken,
                   consumersecretKey, accessTokenSecret, hometimelineData);
       }
       catch (Exception e){
           return hometimelineData.toString()+"is maybe wrong";
       }
    }
    public String userTimeLineJson(Integer counter){

        TreeMap<String,String>usertimelineData=new TreeMap<>();
        usertimelineData.put("count",counter.toString());
        usertimelineData.put("trim_user","1");
        try {
            HttpRequest httpRequest = new HttpRequest();
            return httpRequest.request("GET", USER_TIMELINE_URL, consumerKey, accessToken,
                    consumersecretKey, accessTokenSecret, usertimelineData);
        }
        catch (Exception e){
            return usertimelineData.toString()+"is maybe wrong";
        }
    }
}
