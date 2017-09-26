package twijava;
import twijava.core.net.TwiJavaHttpRequest;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class TwiJava{

    private static String consumerKey;
    private static String consumersecretKey;
    private static String accessToken;
    private static String accessTokenSecret;

    private static final String TIMELINE_URL="statuses/home_timeline.json";
    private static final String USER_TIMELINE_URL="statuses/user_timeline.json";
    private static final String USER_UPDATESTATUS_URL="statuses/update.json";

    private TwiJavaHttpRequest httpRequest=new TwiJavaHttpRequest();

   public static class TwiJavaToken { //APIトークンの設定クラス

        private String ck,cks,at,ats;

        public TwiJavaToken setConsumerKey(String key){
            ck=key;
            return this;
        }
        public TwiJavaToken setConsumerSecretKey(String key){
            cks=key;
            return this;
        }
        public TwiJavaToken setAccessToken(String key){
            at=key;
            return this;
        }
        public TwiJavaToken setAccessTokenSecret(String key){
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
    public String tweet(String text) throws IOException{
       Map<String,String>data=new TreeMap<>();
       data.put("status",text);
       data.put("trim_user","1");
       return httpRequest.post(USER_UPDATESTATUS_URL,data,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret);
    }
   /* public String getHomeTimeLine(String counter){
       Map<String,String>hometimelinedata=new TreeMap<>();
       hometimelinedata.put("count",counter);
       hometimelinedata.put("trim_user","1");
       if(counter.isEmpty()){
           System.out.println("could not find home timeline count value");
           System.exit(0);
       }
       return httpRequest.get(TIMELINE_URL,hometimelinedata);
    }
    public String getUserTimeLine(String counter){
        Map<String,String>usertimelinedata=new TreeMap<>();
        usertimelinedata.put("count",counter);
        usertimelinedata.put("trim_user","1");
        if(counter.isEmpty()){
            System.out.println("could not find user timeline count value");
            System.exit(0);
        }
        return httpRequest.get(USER_TIMELINE_URL,usertimelinedata);
    }*/
}
