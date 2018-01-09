package twijava.oauth;

import twijava.encode.ParamEncoder;

import java.util.TreeMap;

public class OAuthHeaderFactory {

    public static String makeOAuthHeader(String signature,TreeMap<String,String> oAuthParam,
                                         String cks,String ats) throws Exception{

        String compoKey = ParamEncoder.encode(cks)+"&"+ ParamEncoder.encode(ats);

        String oauthSignature = OAuthBasicCodeFactory.makeBasicCode(signature,compoKey);

        String encodedSignature = ParamEncoder.encode(oauthSignature);

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
}

