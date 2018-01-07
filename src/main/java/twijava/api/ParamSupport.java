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
    // TwitterAPI cause error standard utf-8 encode. So replace %20 from +
    public static String twitterUTF8Encode(String params){
        try{
            return URLEncoder.encode(params,"UTF-8").replace("+","%20");
        }
        catch (UnsupportedEncodingException e){
            return e.toString();
        }
    }

    public static String oAuthParamAppending(TreeMap<String, String> param) {
        return param.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}