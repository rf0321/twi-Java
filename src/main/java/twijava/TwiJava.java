/**
 * TwiJava class
 *<p>This class is client</p>
 * /**
 * <p>Token description
 * Below name is key of treeMap for API key
 * ck:OAuth consumer key
 * cks:OAuth consumer secret
 * ac:OAuth access token
 * ats:OAuth access token secret
 * </p>
 */
package twijava;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import twijava.client.requests.TwitterAPIRequests;
import twijava.encode.ParamEncoder;




import java.util.ArrayList;
import java.util.Arrays;
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
    private TreeMap<String,String> apikeysMap(){

        TreeMap<String,String> keys = new TreeMap<>();
        keys.put("ck",consumerKey);
        keys.put("cks",consumerSecretKey);
        keys.put("ac",accessToken);
        keys.put("ats",accessTokenSecret);

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

    public void updateStatus(String text){

        apiRequest().updateStatus(updateStatusContext(text), apikeysMap());
    }

    public String getHomeTimeLine(int counter) {

        return apiRequest().homeStatus(contextForTimeLine(counterCastToString(counter)), apikeysMap());
    }

    public String getUserTimeLine(int counter){

        return apiRequest().userStatus(contextForTimeLine(counterCastToString(counter)),apikeysMap());
    }
}
