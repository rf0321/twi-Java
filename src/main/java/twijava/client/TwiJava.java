/**
 * TwiJava class
 *<p>This class is client side. so, method name is simply</p>
 */
package twijava.client;

import twijava.api.APIRequest;
import twijava.json.util.JsonDecoder;
import twijava.api.ParamSupport;

import java.util.TreeMap;

public class TwiJava{

    private String consumerKey;
    private String consumerSecretKey;
    private String accessToken;
    private String accessTokenSecret;

    private APIRequest apiRequest = new APIRequest();

    public static class TokenInitialize  { //Setting API Token class

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

        public TwiJava sendTokens(){
            return new TwiJava(ck,cks,at,ats);
        }
    }

    private TwiJava(String consumerKey,String consumerSecretKey,
                    String accessToken,String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecretKey = consumerSecretKey;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public String tweet(String text){
        TreeMap<String,String> data = new TreeMap<>();
        data.put("status", ParamSupport.urlEncode(text));
        data.put("trim_user","1");

        return apiRequest.updateStatus(data,consumerKey,consumerSecretKey,
                accessToken,accessTokenSecret);
    }

    public void showHomeTimeLine(Integer counter){
       JsonDecoder.decode(homeTimeLineJson(counter));
    }

    public void showUserTimeLine(Integer counter){
        JsonDecoder.decode(userTimeLineJson(counter));
    }

   private String homeTimeLineJson(Integer counter) {
       TreeMap<String, String> homeTLdata = new TreeMap<>();
       homeTLdata.put("count", counter.toString());
       homeTLdata.put("trim_user", "1");

       return apiRequest.showStatus("home",homeTLdata, consumerKey, consumerSecretKey,
               accessToken, accessTokenSecret);
   }

    private String userTimeLineJson(Integer counter){
        TreeMap<String,String> userTLdata =new TreeMap<>();
        userTLdata.put("count",counter.toString());
        userTLdata.put("trim_user","1");

        return apiRequest.showStatus("user",userTLdata,consumerKey, consumerSecretKey,
                accessToken, accessTokenSecret);
    }
}
