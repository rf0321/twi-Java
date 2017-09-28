package twijava.net;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * Web request support methods
 *
 */
public class APIRequestSupporter{

   public String urlEncode(String s){
       try{
           return URLEncoder.encode(s,"UTF-8");
       }
       catch (UnsupportedEncodingException e){
           return s;
       }
   }

   public String generateNonce(){ //使い捨て暗号ぶっちゃけテキトウな文字でもいい
       Random rnd = new Random();
       return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
   }

   public String formUrlEncodedContent(Map<String, String> data) {
        // Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
        return data.entrySet().stream()
                .map(e -> urlEncode(e.getKey()) + "=" + urlEncode(e.getValue()))
                .collect(Collectors.joining("&"));
    }
    public String generateSignature(String url, String method, Map<String, String> data,
                                    String cks,String ats) {
        Mac mac = null;
        try {
            String key = String.join("&", cks, ats);
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
}



