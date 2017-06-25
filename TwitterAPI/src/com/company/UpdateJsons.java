package com.company;
import  javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class UpdateJsons {
    private final String CONSUMER_KEY;
    private final String CONSUMER_SECRET;
    private final String ACCESS_TOKEN;
    private final String ACCESS_TOKEN_SECRET;

    static final String BASE_URL = "https://api.twitter.com/1.1/";

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
        String res;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(fullUrl).openConnection();
            con.setDoOutput(true); // POSTするときには必要らしい
            con.setRequestMethod("POST");
            header.put("oauth_signature", generateSignature(fullUrl, "POST", merged));
            // POSTのHeader部分
            con.setRequestProperty("Authorization", "OAuth " + header.entrySet().stream()
                    .map(e -> String.format("%s=\"%s\"",
                            urlEncode(e.getKey()), urlEncode(e.getValue())))
                    .collect(Collectors.joining(", ")));
            // POSTのContent部分
            try (OutputStream os = con.getOutputStream();
                 PrintStream ps = new PrintStream(os)) {
                ps.print(formUrlEncodedContent(data));
            }
            // レスポンスの取得
            try (InputStream is = con.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {
                res = br.lines().collect(Collectors.joining());
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return res;
    }

    private Map<String, String> createHeader() {
        // TreeMapはcompareToに基づく順序付けMap
        Map<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", CONSUMER_KEY);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(new Date().getTime()));
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
            SecretKeySpec sk = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "HMAC-SHA1");
            mac = Mac.getInstance("HMAC-SHA1");
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
