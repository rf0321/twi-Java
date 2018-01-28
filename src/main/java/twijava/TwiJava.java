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
    private TreeMap<String,String>updateStatusContent(String text){

        TreeMap<String,String> content = new TreeMap<>();
        content.put("status", ParamEncoder.encode(text));
        content.put("trim_user","1");

        return content;
    }

    @Getter
    private TreeMap<String,String>deleteStatusContent(String idStr){

        TreeMap<String,String> content = new TreeMap<>();
        content.put("id",idStr);

        return content;
    }

    /*@Getter
    private TreeMap<String,String>directMessageContent(String idStr,String text){

        TreeMap<String,String> content = new TreeMap<>();
        content.put("event.type","message_create");
        content.put("message_create.target.recipient_id",idStr);
        content.put("message_create.message_data.text",text);

        return content;
    }*/

    @Getter
    private TreeMap<String,String>timeLineContent(String counter){

        TreeMap<String, String> content = new TreeMap<>();
        content.put("count", counter);
        content.put("trim_user", "1");

        return content;
    }

    @Getter
    private TreeMap<String,String> searchContent(String query,String count) {

        TreeMap<String, String> content = new TreeMap<>();
        content.put("q", ParamEncoder.encode(query));
        content.put("count", count);
        return content;
    }

    @Getter
    private TreeMap<String,String> profileContent(String name){

        TreeMap<String,String>content = new TreeMap<>();
        content.put("screen_name",name);

        return content;
    }

    @Getter
    private String counterCastToString(int i){

        return String.valueOf(i);
    }

    private TwitterAPIRequests apiRequest(){

        return new TwitterAPIRequests();
    }

    public void tweet(String text){

        apiRequest().updateStatus(updateStatusContent(text),apikeysMap());
    }

    public void deleteTweet(String idStr){

        apiRequest().deleteStatus(deleteStatusContent(idStr),apikeysMap());
    }

    /*public void sendDirectMessage(String idStr,String text){

        apiRequest().messageStatus(directMessageContent(idStr,text),apikeysMap());
    }*/

    public String getUserProfile(String screenName){

        return apiRequest().profileStatus(profileContent(screenName),apikeysMap());
    }

    public String getHomeTimeLine(int counter) {

        return apiRequest().homeStatus(timeLineContent(counterCastToString(counter)),apikeysMap());
    }

    public String getUserTimeLine(int counter){

        return apiRequest().userStatus(timeLineContent(counterCastToString(counter)),apikeysMap());
    }

    public String searchTweet(String word, int count){

        return apiRequest().searchStatus(searchContent(word,counterCastToString(count)),apikeysMap());
    }
}
