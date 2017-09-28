package twijava;
import twijava.net.TwiJavaHttpRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * 認証にいるキー
 * @param consumerKey
 * @param consumerSecretKey
 * @param accessToken
 * @param accessTokenSecret
 */
public class TwiJava{

    private String consumerKey;
    private String consumersecretKey;
    private String accessToken;
    private String accessTokenSecret;

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
    public String tweet(String text) throws Exception{
       Map<String,String>data=new TreeMap<>();
       data.put("status",text);
       data.put("trim_user","1");

       if(text.length()>140){
           System.out.println("You cannot tweet because the charlength 140 over");
       }
       return httpRequest.testPost(USER_UPDATESTATUS_URL,data,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret);
    }
   /* public String getHomeTimeLine(String counter) throws IOException{
       Map<String,String>hometimelinedata=new TreeMap<>();
       hometimelinedata.put("count",counter);
       hometimelinedata.put("trim_user","1");
       if(counter.isEmpty()){
           System.out.println("could not find home timeline count value");
           System.exit(0);
       }
       return httpRequest.get(TIMELINE_URL,hometimelinedata,consumerKey,accessToken,
               consumersecretKey,accessTokenSecret);
    }
    public String getUserTimeLine(String counter) throws IOException{
        Map<String, String> usertimelinedata = new TreeMap<>();
        usertimelinedata.put("count", counter);
        usertimelinedata.put("trim_user", "1");
        if (counter.isEmpty()) {
            System.out.println("could not find user timeline count value");
            System.exit(0);
        }
        return httpRequest.get(USER_TIMELINE_URL, usertimelinedata,consumerKey,accessToken
                ,consumersecretKey,accessTokenSecret);
    }*/
}
