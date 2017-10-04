package twijava.net;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class OAuthUtil { //Authorization components class
    private static final String UTF8 = "UTF-8";

    private String callBackUrl="";

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

    public static String generateSignature(String method,String url,TreeMap<String,String>urlParam,
                                           TreeMap<String,String>oauthParam)throws Exception{
        StringBuffer paramString=new StringBuffer();
        TreeMap<String,String>treeMap= new TreeMap<>();

        //Use lambda substitution to TreeMap
        //keySet returns Set<E>
        //Set have Iterator on super interface,so we can use foreach.

        urlParam.keySet().forEach(key->treeMap.put(key, urlParam.get(key)));
        oauthParam.keySet().forEach(key->treeMap.put(key,oauthParam.get(key)));

        treeMap.keySet().forEach(keys->paramString.append("&" + keys + "=" + treeMap.headMap(keys)));

        String temp="%s&%s&%s";
        String signature =String.format(temp, URLEncoder.encode(method,UTF8),
                URLEncoder.encode(url,UTF8),URLEncoder.encode(paramString.toString(),UTF8));
        return signature;
    }

    public static String generateBasicCode(String base,String key){
        Mac m=null;
        byte[]text=null;

        try {
             byte[]keyBytes=key.getBytes();
             SecretKeySpec keySpec=new SecretKeySpec(keyBytes,"HmacSHA1");
             m = Mac.getInstance("HmacSHA1");
             m.init(keySpec);

            text=base.getBytes();

        }
        catch (InvalidKeyException|NoSuchAlgorithmException e){
               e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(
                m.doFinal(text)).trim();
    }

    public static String makeOAuthHeader(String signature,TreeMap<String,String>tree,
                                  String cks,String ats) throws Exception{

        String compoKey=URLEncoder.encode(cks,UTF8)+"&"+ URLEncoder.encode(ats,UTF8);
        String oauthSignature=generateBasicCode(signature,compoKey);
        String oauthEncoded=URLEncoder.encode(oauthSignature,UTF8);

        String authHeaderTemp="OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", " +
                "oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

       return String.format(authHeaderTemp,
                tree.headMap("oauth_consumer_key"),
                tree.headMap("oauth_nonce"),
                oauthEncoded,
                tree.headMap("oauth_signature_method"),
                tree.headMap("oauth_timestamp"),
                tree.headMap("oauth_token"),
                tree.headMap("oauth_version"));
    }
    public static String getURLWithParam(String url,TreeMap<String,String>paramMap){
        StringBuffer strBuffer=new StringBuffer(url);
        TreeMap<String,String>treeMap=new TreeMap<>();

        paramMap.keySet().forEach(keys->treeMap.put(keys,paramMap.get(keys)));
        treeMap.keySet().forEach(keys->appending(treeMap,keys,strBuffer));

        return strBuffer.toString();
    }
    private static void appending(TreeMap<String,String>treeMap,Object o,StringBuffer stringBuffer){
        if(o==treeMap.firstKey()){
            stringBuffer.append("?");
        }
        else {
            stringBuffer.append("?");
            stringBuffer.append(o+"="+treeMap.get(o));
        }
    }
   /* public String setcallbackURL(String url){
        callBackUrl=url;
        return callBackUrl;
    }
    public String getCallBackUrl(){
        return callBackUrl;
    }*/
}
