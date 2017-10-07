package twijava.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;



/**
 * Web request support methods
 */
public class RequestSupporter {

   public static String urlEncode(String s){
       final String UTF_8="UTF-8";
       try{
          return URLEncoder.encode(s,UTF_8).replace("+", "%20");
       }
       catch (UnsupportedEncodingException e){
           throw new RuntimeException(e);
       }
   }

   public static String generateNonce(){ //使い捨て暗号ぶっちゃけテキトウな文字でもいい
       Random rnd = new Random();
       return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
   }

   public static String formUrlEncodedContent(Map<String, String> data) {
        // Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
        return data.entrySet().stream()
                .map(e -> urlEncode(e.getKey()) + "=" + urlEncode(e.getValue()))
                .collect(Collectors.joining("&"));
    }
}









