
import twijava.net.RequestSupporter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;


public class TestOAuthUtil {
    private static final String UTF_8="UTF-8";
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";
    private static final String USER_UPDATESTATUS_URL="statuses/update.json";

    private String consumerKey;
    private String consumerkeySecret;
    private String accessToken;
    private String accessTokenSecret;


    String sendurl = TWITTERAPI_BASEURL + USER_UPDATESTATUS_URL;


    public TreeMap<String,String>oAuthParam(){

        TreeMap<String,String>data=new TreeMap<>();
        data.put("status","Hello World");

        data.put("trim_user","1");
        data.put("oauth_consumer_key", consumerKey);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", RequestSupporter.generateNonce());
        data.put("oauth_token", accessToken);
        data.put("oauth_version", "1.0");
        data.put("oauth_signature",gensignature(sendurl,"POST",data,consumerkeySecret,consumerkeySecret));
        return data;
    }
    public String sendrequest() throws IOException{
        Map<String,String>header=oAuthParam();
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", RequestSupporter.urlEncode(e.getKey()),RequestSupporter.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));
        TestHttpRequest testHttpRequest=new TestHttpRequest();
        return testHttpRequest.post(headerString,sendurl);
    }

    public String gensignature(String url,String method, Map<String, String> data,
                                    String cks,String ats) {
        Mac mac = null;
        try {
            String key = String.join("&", cks, ats);
            // シークレットを合わせてSHA1の秘密鍵を作る

            SecretKeySpec sk = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "HmacSHA1");
            mac = Mac.getInstance("HmacSHA1");
            mac.init(sk);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) { //おそらく出ないであろう例外
            e.printStackTrace();
        }
        String signature = String.join("&", method,
                RequestSupporter.urlEncode(url),
                RequestSupporter.urlEncode(RequestSupporter.formUrlEncodedContent(data)));
        // ハッシュ化したシグネチャをBase64に変化する
        return Base64.getEncoder().encodeToString(
                mac.doFinal(signature.getBytes(StandardCharsets.US_ASCII)));
    }
}
