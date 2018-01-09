package twijava.oauth;

import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OAuthSupportParamFactory {

    public static String generateNonce() {

        Random rnd = new Random();

        return String.valueOf(123400 + rnd.nextInt(9999999 - 123400));
    }

    public static String oAuthParamAppending(TreeMap<String, String> param) {
        return param.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}