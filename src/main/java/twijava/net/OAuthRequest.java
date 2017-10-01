package twijava.net;
import twijava.net.core.HttpRequest;
import java.util.*;
import java.util.stream.Collectors;


public class OAuthRequest {
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";

    private APIRequestSupporter api = new APIRequestSupporter();


    public String getrequestSender(String url,Map<String,String>data,String ck, String at,
                                String cks, String ats) throws Exception{
        String urlWithSignature=TWITTERAPI_BASEURL+url;

        Map<String,String>header=createHeader(ck,at);
        Map<String,String>merged=new TreeMap<>(header);
        merged.putAll(data);
        header.put("oauth_signature",api.generateSignature(urlWithSignature, "GET", merged,cks,ats));

        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", api.urlEncode(e.getKey()), api.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));

        HttpRequest httpRequest=new HttpRequest();
        return httpRequest.get(urlWithSignature,data,headerString);
    }

    private Map<String, String> createHeader(String ck,String ac) {
        //Headerにおける要素

        Map<String, String> data = new TreeMap<>();
        data.put("oauth_consumer_key", ck);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", String.valueOf(Calendar
                .getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime() / 1000));
        data.put("oauth_nonce", api.generateNonce());
        data.put("oauth_token", ac);
        data.put("oauth_version", "1.0");
        return data;
    }
}
