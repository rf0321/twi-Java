package twijava.net;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import twijava.core.TwiJavaMainSystem;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class TwiJavaHttpRequest {
    private static final String API_BASE_URL = "https://api.twitter.com/1.1/";
    private static final String TIMELINE_URL="statuses/home_timeline.json";
    private static final String USER_TIMELINE_URL="statuses/user_timeline.json";
    private static final String USER_UPDATESTATUS_URL="statuses/update.json";

    TwiJavaMainSystem twiJavaMainSystem;
    public String post(Map<String,String>data)throws IOException{
        String posturl=API_BASE_URL+USER_UPDATESTATUS_URL;

        Map<String,String>header=createHeader();
        Map<String,String>merged=new TreeMap<>();

        header.put("oauth_signature", generateAPIsignature(posturl, "POST", merged));
        // Authorizationの要素が揃ったので文字列にする(エスケープを忘れない)
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", urlEncode(e.getKey()), urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));
        // POSTするデータのHttpComponentsの仕様
        List<NameValuePair> postData = data.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(posturl);
            post.setHeader(HttpHeaders.AUTHORIZATION, headerString);
            // 入出力でUTF-8を明示する
            post.setEntity(new UrlEncodedFormEntity(postData, StandardCharsets.UTF_8));
            return client.execute(post, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
        }
    }
    /*public String get(){

    }*/
    private String generateAPIsignature(String url, String method, Map<String, String> data) {
        Mac mac = null;
        try {
            String key = String.join("&", twiJavaMainSystem.consumerKeySecret,
                    twiJavaMainSystem.accessTokenSecret);
            // シークレットを合わせてSHA1の秘密鍵を作る
            SecretKeySpec sk = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "HmacSHA1");
            mac = Mac.getInstance("HmacSHA1");
            mac.init(sk);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) { // おそらく出ないであろう例外
        }
        // 繋げる
        String signature = String.join("&",
                method, urlEncode(url), urlEncode(formUrlEncodedContent(data)));
        // ハッシュ化したシグネチャをBase64に変化する
        return Base64.getEncoder().encodeToString(
                mac.doFinal(signature.getBytes(StandardCharsets.US_ASCII)));
    }
    private Map<String, String> createHeader() {
        // TreeMapはcompareToに基づく順序付けMap
        Map<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key",twiJavaMainSystem.consumerKey);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", GenerateNonce());
        data.put("oauth_token", twiJavaMainSystem.accessToken);
        data.put("oauth_version", "1.0");
        return data;
    }
    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
    private String GenerateNonce() {
        Random rnd = new Random();
        return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
    }
    private String formUrlEncodedContent(Map<String, String> data) {
        // Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
        return data.entrySet().stream()
                .map(e -> urlEncode(e.getKey()) + "=" + urlEncode(e.getValue()))
                .collect(Collectors.joining("&"));
    }
}
