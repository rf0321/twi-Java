/**
 * TwiJava class
 *<p>This is client class</p>
 */
package twijava;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.TreeMap;


public class TwiJava{
    /**
     * @param OAuth Consumer Key
     */
    private static String consumerKey;
    /**
     * @param OAuth Consumer Secret Key
     */
    private static String consumerSecretKey;
    /**
     * @param OAuth Access Token
     */
    private static String accessToken;
    /**
     * @param OAuth Access Token Secret
     */
    private static String accessTokenSecret;

    private TwitterRequests requests = new TwitterRequests();

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
    public static TreeMap<String,String> getApiKeysMap(){

        TreeMap<String,String> keys = new TreeMap<>();
        keys.put("ck",consumerKey);
        keys.put("cks",consumerSecretKey);
        keys.put("ac",accessToken);
        keys.put("ats",accessTokenSecret);

        return keys;
    }

    public void tweet(String text){
        requests.tweet(text);
    }

    public void deleteTweet(String idStr){
        requests.deleteTweet(idStr);
    }

    public String searchTweet(String query){
        return requests.searchTweet(query);
    }

    public String getUserProfile(String screen_name){
        return requests.getUserProfile(screen_name);
    }

    public String getFollowerList(){
        return requests.getFollowerList();
    }

    public String getFriendList(){
        return requests.getFriendList();
    }

    public String getHomeTimeLine(int count){
        return requests.getHomeTimeLine(count);
    }

    public String getUserTimeLine(int count){
        return requests.getUserTimeLine(count);
    }
}
