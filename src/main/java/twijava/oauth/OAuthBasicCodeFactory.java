package twijava.oauth;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OAuthBasicCodeFactory {

    public static String makeBasicCode(String base,String key) throws Exception{

        SecretKey secretKey;
        byte[]keyByte = key.getBytes();
        secretKey = new SecretKeySpec(keyByte,"HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[]text=base.getBytes(StandardCharsets.US_ASCII);

        return Base64.getEncoder().encodeToString(mac.doFinal(text)).trim();
    }
}
