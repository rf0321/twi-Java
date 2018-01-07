package twijava.oauth;

import twijava.api.ParamSupport;

import java.util.TreeMap;

public class OAuthSignatureFactory {

    public static String makeSignature(String method, String url,
                                       TreeMap<String,String> urlParam, TreeMap<String,String>oauthParam){
        TreeMap<String,String>treeMap= new TreeMap<>();

        treeMap.putAll(urlParam);
        treeMap.putAll(oauthParam);

        String paramStr= ParamSupport.oAuthParamAppending(treeMap);

        String temp="%s&%s&%s";

        return String.format(temp,
                ParamSupport.twitterUTF8Encode(method),
                ParamSupport.twitterUTF8Encode(url),
                ParamSupport.twitterUTF8Encode(paramStr));

    }
}
