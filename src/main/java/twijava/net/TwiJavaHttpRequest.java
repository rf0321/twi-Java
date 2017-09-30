package twijava.net;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.http.util.EntityUtils;
import twijava.core.AuthorizationHttpCore;


public class TwiJavaHttpRequest {
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";

    private APIRequestSupporter api = new APIRequestSupporter();

    public String post(String url, Map<String, String> data,
                           String ck, String at, String cks, String ats) throws Exception {
        String sendurl = TWITTERAPI_BASEURL + url;

        Map<String, String> header = createHeader(ck, at);
        Map<String, String> merged = new TreeMap<>(header);
        merged.putAll(data);
        header.put("oauth_signature", api.generateSignature(sendurl, "POST", merged, cks, ats));
        // Authorizationの要素が揃ったので文字列にする(エスケープを忘れない)
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", api.urlEncode(e.getKey()), api.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));
        // POSTするデータのHttpComponentsの仕様
        List<NameValuePair> postData = data.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(sendurl);
            post.setHeader(HttpHeaders.AUTHORIZATION, headerString);
            // 入出力でUTF-8を明示する
            post.setEntity(new UrlEncodedFormEntity(postData, StandardCharsets.UTF_8));
            // レスポンスボディを勝手に文字列にして返してくれるおまじない
            return client.execute(post, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
        }
    }
    public String get(String url,Map<String,String>timeLineData,String ck,String ac,String cks,String ats)throws Exception {
        String fullurl=TWITTERAPI_BASEURL+url;
        Map<String,String>header=createHeader(ck,ac);
        Map<String,String>AuthticationMerged=new TreeMap<>(header);
        AuthticationMerged.putAll(timeLineData);

        header.put("oauth_signature",api.generateSignature(fullurl, "GET", AuthticationMerged,cks,ats));
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", api.urlEncode(e.getKey()), api.urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));

        fullurl += "?" + api.formUrlEncodedContent(timeLineData);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpUriRequest request;
            request=new HttpGet(fullurl);
            System.out.println(request);
            request.setHeader(HttpHeaders.AUTHORIZATION, headerString);
           return client.execute(request, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
        }

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
