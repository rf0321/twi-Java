package twijava.net;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;


public class OAuthUtil { //Authorization components class
    private static final String UTF8 = "UTF-8";

    public static TreeMap<String, String> getOAuthParam(String ck,String ac) {
        //Headerにおける要素

        RequestSupporter api = new RequestSupporter();
        TreeMap<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", ck);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", api.generateNonce());
        data.put("oauth_token", ac);
        data.put("oauth_version", "1.0");
        return data;
    }

    public static String generateSignature(String method,String url,
                                           TreeMap<String,String>urlParam,TreeMap<String,String>oauthParam)throws Exception{
        StringBuffer paramString=new StringBuffer();
        TreeMap<String,String>treeMap= new TreeMap<>();

        treeMap.putAll(urlParam);
        treeMap.putAll(oauthParam);

        for(Map.Entry<String,String>param:treeMap.entrySet()){
           if(!param.equals(treeMap.firstEntry())){
               paramString.append("&");
           }
           paramString.append(param.getKey()+"="+param.getValue());
        }

        String temp="%s&%s&%s";
        String signature =String.format(temp, URLEncoder.encode(method,UTF8),
                URLEncoder.encode(url,UTF8),URLEncoder.encode(paramString.toString(),UTF8));
        return signature;
    }

    public static String generateBasicCode(String base,String key) throws Exception{

        SecretKey secretKey;
        byte[]keyByte=key.getBytes();
        secretKey=new SecretKeySpec(keyByte,"HmacSHA1");

        Mac mac=Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[]text=base.getBytes();

        return Base64.getEncoder().encodeToString(mac.doFinal(text)).trim();
    }


    public static String makeOAuthHeader(String signature,TreeMap<String,String>oAuthParam,
                                   String cks,String ats) throws Exception{

        String compoKey=URLEncoder.encode(cks,"UTF-8")+"&"+URLEncoder.encode(ats,"UTF-8");
        String oauthSignature=generateBasicCode(signature,compoKey);

        String encodedSignature=URLEncoder.encode(oauthSignature,"UTF-8");

       //esape data strings
       String authHeaderTemp="OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", " +
                "oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

       return String.format(authHeaderTemp, oAuthParam.get("oauth_consumer_key"),
               oAuthParam.get("oauth_nonce"),
               encodedSignature,
               oAuthParam.get("oauth_signature_method"),
               oAuthParam.get("oauth_timestamp"),
               oAuthParam.get("oauth_token"),
               oAuthParam.get("oauth_version"));
    }
    public static String getURLWithParam(String url,TreeMap<String,String>paramMap){
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
    /*private static void appending(TreeMap<String,String>treeMap,Object o,StringBuffer stringBuffer){
        if(o==treeMap.firstKey()){
            stringBuffer.append("?");
        }
        else {
            stringBuffer.append("&");
        }
        stringBuffer.append(o+"="+treeMap.get(o));
    }

    public String setcallbackURL(String url){
        callBackUrl=url;
        return callBackUrl;
    }
    public String getCallBackUrl(){
        return callBackUrl;
    }*/
}
