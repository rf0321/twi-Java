package twijava;
import twijava.net.core.HttpRequest;

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
        TreeMap<String,String>data=new TreeMap<>();
        data.put("status",text);
        data.put("trim_user","1");

        if(text.length()>140){
            System.out.println("[Request Error:You cant this tweet because the content charactor over 140]");
            System.exit(0);
        }
        HttpRequest request=new HttpRequest();
        return request.post(USER_UPDATESTATUS_URL,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret,data);
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
        JsonDecoder decoder=new JsonDecoder();
        isparse=parse;
        if(isparse=true) {
            decoder.decode(JsonUserTimeline(counter));
        }
        else if(isparse=false){
            System.out.println(JsonUserTimeline(counter));
        }
    }*/
}
