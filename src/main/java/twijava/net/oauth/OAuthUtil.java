package twijava.net.oauth;

import twijava.net.ParamSupporter;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * ck:OAuth consumer key
 * cks:OAuth consumer secret
 * at:OAuth access token
 * ats:OAuth access token secret
 */

public class OAuthUtil {  //Authorization components class

    public static TreeMap<String, String> getOAuthParam(String ck,String ac) {

        //Components of need to authorization

        TreeMap<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", ck);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", ParamSupporter.generateNonce());
        data.put("oauth_token", ac);
        data.put("oauth_version", "1.0");

        return data;
    }

    public static String generateSignature(String method,String url,
                                           TreeMap<String,String>urlParam,TreeMap<String,String>oauthParam)throws Exception{
        TreeMap<String,String>treeMap= new TreeMap<>();
       // StringBuffer paramString=new StringBuffer();

        treeMap.putAll(urlParam);
        treeMap.putAll(oauthParam);

        /*for(Map.Entry<String,String>param:treeMap.entrySet()){
            if(!param.equals(treeMap.firstEntry())){
                paramString.append("&");
            }
            paramString.append(param.getKey()+"="+param.getValue());
        }*/
        String paramStr=ParamSupporter.oAuthParamAppending(treeMap);

        String temp="%s&%s&%s";

        String signature =String.format(temp,
                ParamSupporter.urlEncode(method),
                ParamSupporter.urlEncode(url),
                ParamSupporter.urlEncode(paramStr));

        return signature;
    }

    public static String generateBasicCode(String base,String key) throws Exception{

        SecretKey secretKey;
        byte[]keyByte=key.getBytes();
        secretKey=new SecretKeySpec(keyByte,"HmacSHA1");

        Mac mac=Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[]text=base.getBytes(StandardCharsets.US_ASCII);

        return Base64.getEncoder().encodeToString(mac.doFinal(text)).trim();
    }

    public static String makeOAuthHeader(String signature,TreeMap<String,String>oAuthParam,
                                   String cks,String ats) throws Exception{

        String compoKey=ParamSupporter.urlEncode(cks)+"&"+ParamSupporter.urlEncode(ats);
        String oauthSignature=generateBasicCode(signature,compoKey);

        String encodedSignature=ParamSupporter.urlEncode(oauthSignature);

       //esape data strings
        String authHeaderTemp="OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", " +
                "oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

       return String.format(authHeaderTemp,
               oAuthParam.get("oauth_consumer_key"),
               oAuthParam.get("oauth_nonce"),
               encodedSignature,
               oAuthParam.get("oauth_signature_method"),
               oAuthParam.get("oauth_timestamp"),
               oAuthParam.get("oauth_token"),
               oAuthParam.get("oauth_version"));
    }

    public static String makeURLwithParam(String url,TreeMap<String,String>paramMap){
        StringBuffer strBuffer=new StringBuffer(url);
        TreeMap<String,String>treeMap=new TreeMap<>();
        treeMap.putAll(paramMap);

        for (Map.Entry<String, String> paramEntry : treeMap.entrySet()) {
            if (paramEntry.equals(treeMap.firstEntry())) {
                strBuffer.append("?");
            } else {
                strBuffer.append("&");
            }
            strBuffer.append(paramEntry.getKey() + "=" + paramEntry.getValue());
        }

        return strBuffer.toString();
    }
}
