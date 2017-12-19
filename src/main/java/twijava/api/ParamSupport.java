package twijava.api;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ParamSupport {

    public static String generateNonce() {
        Random rnd = new Random();
        return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
    }

    public static String urlEncode(String s){
        try{
            return URLEncoder.encode(s,"UTF-8").replace("+","%20");
        }
        catch (UnsupportedEncodingException e){
            return s;
        }
    }
    public static String oAuthParamAppending(TreeMap<String, String> param) {
        // Map<K, V>はEntry<K, V>になりkey=value&key=...の形で文字列に変換される
        return param.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}














