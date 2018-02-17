package twijava;

import java.util.TreeMap;

public class APIKeyFactory {

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

    public void buildKey(String consumerKey,String consumerSecretKey,
                          String accessToken,String accessTokenSecret){
        this.consumerKey = consumerKey;
        this.consumerSecretKey = consumerSecretKey;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public static TreeMap<String,String>apiKeyMap(){

        TreeMap<String,String> keys = new TreeMap<>();
        keys.put("ck",consumerKey);
        keys.put("cks",consumerSecretKey);
        keys.put("ac",accessToken);
        keys.put("ats",accessTokenSecret);

        return keys;
    }
}
