package twijava.oauth;

import twijava.encode.ParamEncoder;

import java.util.TreeMap;

public class OAuthSignatureFactory {

    public static String makeSignature(String method, String url,
                                       TreeMap<String,String> urlParam, TreeMap<String,String>oauthParam){
        TreeMap<String,String>treeMap= new TreeMap<>();

        treeMap.putAll(urlParam);
        treeMap.putAll(oauthParam);

        String paramStr= OAuthSupportParamFactory.oAuthParamAppending(treeMap);

        String temp="%s&%s&%s";

        return String.format(temp,
                ParamEncoder.encode(method),
                ParamEncoder.encode(url),
                ParamEncoder.encode(paramStr));
    }
}
