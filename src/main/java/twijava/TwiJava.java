/**
 * TwiJava class
 *<p>This class is client side. so, method name is simply</p>
 * /**
 * <p>Token description
 * ck:OAuth consumer key
 * cks:OAuth consumer secret
 * at:OAuth access token
 * ats:OAuth access token secret
 * </p>
 */
package twijava;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import twijava.client.requests.TwitterAPIRequests;
import twijava.encode.ParamEncoder;
import twijava.json.util.JsonDecoder;
import twijava.oauth.OAuthSupportParamFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class TwiJava{

    private String consumerKey;

    private String consumerSecretKey;

    private String accessToken;

    private String accessTokenSecret;

    @Setter
    public void setConsumerSecretKey(String consumerSecretKey) {

        this.consumerSecretKey = consumerSecretKey;
    }

    @Setter
    public void setConsumerKey(String consumerKey) {

        this.consumerKey = consumerKey;
    }

    @Setter
    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;
    }

    @Setter
    public void setAccessTokenSecret(String accessTokenSecret){

        this.accessTokenSecret = accessTokenSecret;
    }

    @Getter
    private List<String> apikeysList() {

        List<String> keys = new ArrayList<>();
        keys.add(consumerKey);
        keys.add(consumerSecretKey);
        keys.add(accessToken);
        keys.add(accessTokenSecret);

        return keys;
    }

    @Getter
    private TreeMap<String,String>updateStatusContext(String text){

        TreeMap<String,String> context = new TreeMap<>();
        context.put("status", ParamEncoder.encode(text));
        context.put("trim_user","1");

        return context;
    }

    @Getter
    private TreeMap<String,String>contextForTimeLine(String counter){

        TreeMap<String, String> context = new TreeMap<>();
        context.put("count", counter);
        context.put("trim_user", "1");

        return context;
    }

    @Getter
    private String counterCastToString(int i){

        return String.valueOf(i);
    }

    private TwitterAPIRequests apiRequest(){

        return new TwitterAPIRequests();
    }

    public String tweet(String text){

        return apiRequest().updateStatus(updateStatusContext(text), apikeysList());
    }

    public void getHomeTimeLine(int counter) {

        String count = counterCastToString(counter);

        String json = apiRequest().homeStatus(contextForTimeLine(count),apikeysList());

        JsonDecoder.decode(json);
    }

    public void getUserTimeLine(int counter){

        String count = counterCastToString(counter);

        String json = apiRequest().userStatus(contextForTimeLine(count),apikeysList());

        JsonDecoder.decode(json);
    }
}
