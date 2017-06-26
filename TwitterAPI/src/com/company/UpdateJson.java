package com.company;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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

public class UpdateJson {
    private final String CONSUMER_KEY;
    private final String CONSUMER_SECRET;
    private final String ACCESS_TOKEN;
    private final String ACCESS_TOKEN_SECRET;

    private static final String BASE_URL = "https://api.twitter.com/1.1/";

    public UpdateJson(String CONSUMER_KEY, String CONSUMER_SECRET, String ACCESS_TOKEN, String ACCESS_TOKEN_SECRET) {
        this.CONSUMER_KEY = CONSUMER_KEY;
        this.CONSUMER_SECRET = CONSUMER_SECRET;
        this.ACCESS_TOKEN = ACCESS_TOKEN;
        this.ACCESS_TOKEN_SECRET = ACCESS_TOKEN_SECRET;
    }
    public String tweet(String text) throws IOException {
        Map<String, String> data = new TreeMap<>();
        data.put("status", text);
        data.put("trim_user", "1");
        return SendRequest("statuses/update.json", data);
    }

    public String SendRequest(String url, Map<String, String> data) throws IOException {
        String fullUrl = BASE_URL + url;
        Map<String, String> header = createHeader();
        Map<String, String> merged = new TreeMap<>(header);
        merged.putAll(data);
        header.put("oauth_signature", generateSignature(fullUrl, "POST", merged));
        // Authorizationの要素が揃ったので文字列にする(エスケープを忘れない)
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", urlEncode(e.getKey()), urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));
        // POSTするデータのHttpComponentsの仕様
        List<NameValuePair> postData = data.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(fullUrl);
            post.setHeader(HttpHeaders.AUTHORIZATION, headerString);
            // 入出力でUTF-8を明示する
            post.setEntity(new UrlEncodedFormEntity(postData, StandardCharsets.UTF_8));
            // レスポンスボディを勝手に文字列にして返してくれるおまじない
            return client.execute(post, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
        }
    }

    private Map<String, String> createHeader() {
        // TreeMapはcompareToに基づく順序付けMap
        Map<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", CONSUMER_KEY);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", GenerateNonce());
        data.put("oauth_token", ACCESS_TOKEN);
        data.put("oauth_version", "1.0");
        return data;
    }

    private String generateSignature(String url, String method, Map<String, String> data) {
        Mac mac = null;
        try {
            String key = String.join("&", CONSUMER_SECRET, ACCESS_TOKEN_SECRET);
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

    private String formUrlEncodedContent(Map<String, String> data) {
        // Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
        return data.entrySet().stream()
                .map(e -> urlEncode(e.getKey()) + "=" + urlEncode(e.getValue()))
                .collect(Collectors.joining("&"));
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
}
