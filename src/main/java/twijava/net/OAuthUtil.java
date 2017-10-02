package twijava.net;

import twijava.net.core.HttpRequest;
import twijava.net.data.surpport.BasicSupporter;
import twijava.net.data.surpport.NameValuePairs;
import java.util.*;
import java.util.stream.Collectors;


public class OAuthUtil { //Authorization components class
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";
    private Map<String, String> createHeader(String ck,String ac) {
        //Headerにおける要素

        RequestSupporter api=new RequestSupporter();
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

    public String getrequestSender(String url,Map<String,String>data,String ck, String at,
                                String cks, String ats) throws Exception{
        RequestSupporter api = new RequestSupporter();

        String urlWithSignature=TWITTERAPI_BASEURL+url;

        Map<String,String>header=createHeader(ck,at);
        Map<String,String>merged=new TreeMap<>(header);
        merged.putAll(data);
        header.put("oauth_signature",api.generateSignature(urlWithSignature, merged,cks,ats));
        String signature = String.join("&",
                api.urlEncode(url), api.urlEncode(api.formUrlEncodedContent(merged)));
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", api.urlEncode(e.getKey()), api.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));

        HttpRequest httpRequest=new HttpRequest();
        return headerString;
    }

    public String oauthPostSender(String url,Map<String,String>data,String ck,
                                    String at,String cks,String ats) throws Exception {
        RequestSupporter api = new RequestSupporter();

        String urlWithSignature = TWITTERAPI_BASEURL + url;
        Map<String, String> header = createHeader(ck, at);
        Map<String, String> merged = new TreeMap<>(header);
        merged.putAll(data);
        header.put("oauth_signature", api.generateSignature(urlWithSignature, merged, cks, ats));

        String autheader = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", api.urlEncode(e.getKey()), api.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));

        List<NameValuePairs>postData=data.entrySet().stream()
                .map(e-> new BasicSupporter(e.getKey(),e.getValue()))
                .collect(Collectors.toList());
         HttpRequest request=new HttpRequest();
         return request.post(urlWithSignature,postData,autheader);
    }
}
