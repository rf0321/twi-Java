package twijava;
import twijava.json.util.TwiJavaJsonDecoder;
import twijava.net.OAuthUtil;
import java.util.Map;
import java.util.TreeMap;

/**
 * 認証にいるキー
 *  consumerKey
 *  consumerSecretKey
 *  accessToken
 *  accessTokenSecret
 */
public class TwiJava{

    private String consumerKey;
    private String consumersecretKey;
    private String accessToken;
    private String accessTokenSecret;

    public static final String TIMELINE_URL="statuses/home_timeline.json";
    private static final String USER_TIMELINE_URL="statuses/user_timeline.json";
    private static final String USER_UPDATESTATUS_URL="statuses/update.json";


    private boolean isparse;

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
    public String tweet(String text) throws Exception{
        Map<String,String>data=new TreeMap<>();
        data.put("status",text);
        data.put("trim_user","1");

        if(text.length()>140){
            System.out.println("[Request Error:You cant this tweet because the content charactor over 140]");
            System.exit(0);
        }
        OAuthUtil request=new OAuthUtil();
       return request.oauthPostSender(USER_UPDATESTATUS_URL,data,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret);
    }
    private String HomeTimelineJson(Integer counter) throws Exception{
       Map<String,String>hometimelineData=new TreeMap<>();
       hometimelineData.put("count",counter.toString());
       hometimelineData.put("trim_user","1");
        OAuthUtil request=new OAuthUtil();
        return request.getrequestSender(TIMELINE_URL,hometimelineData,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret);
    }
    public void getHomeTimeLine(Integer counter,boolean parse) throws Exception{
        TwiJavaJsonDecoder decoder=new TwiJavaJsonDecoder();
        isparse=parse;
        if(isparse=true) {
            decoder.decode(HomeTimelineJson(counter));
        }
        else if(isparse=false){
            System.out.println(HomeTimelineJson(counter));
        }
    }

    /*private String JsonUserTimeline(Integer counter)throws Exception{
        Map<String,String>usertimelineData=new TreeMap<>();
        usertimelineData.put("count",counter.toString());
        usertimelineData.put("trim_user","1");
        HttpRequest httpRequest=new HttpRequest();
        return httpRequest.get(USER_TIMELINE_URL,usertimelineData,consumerKey,accessToken,
                consumersecretKey,accessTokenSecret);
    }
    /*public void getUserTimeLine(Integer counter,boolean parse) throws Exception{
        TwiJavaJsonDecoder decoder=new TwiJavaJsonDecoder();
        isparse=parse;
        if(isparse=true) {
            decoder.decode(JsonUserTimeline(counter));
        }
        else if(isparse=false){
            System.out.println(JsonUserTimeline(counter));
        }
    }*/
}
