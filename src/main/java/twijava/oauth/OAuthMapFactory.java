package twijava.oauth;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.TreeMap;


public class OAuthMapFactory {

    public static TreeMap<String, String> getOAuthMap(String ck, String ac) {

        //Components of need to authorization
        TreeMap<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", ck);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", OAuthSupportParamFactory.generateNonce());
        data.put("oauth_token", ac);
        data.put("oauth_version", "1.0");

        return data;
    }
}
