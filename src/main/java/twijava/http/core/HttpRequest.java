package twijava.http.core;

import twijava.APIKeyFactory;
import twijava.url.TwitterApiURLs;
import twijava.http.HttpResponseHandler;
import twijava.oauth.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;


public class HttpRequest {

    public String get(String uri,TreeMap<String,String>data){
        return makeSendParam("GET",uri,data);
    }

    public String post(String uri,TreeMap<String,String>data){
        return makeSendParam("POST",uri,data);
    }

    private String makeSendParam(String method, String uri, TreeMap<String,String>data) {

        try {
            TreeMap<String,String>keyMap = APIKeyFactory.apiKeyMap();

            StringBuilder urlBuilder = new StringBuilder();

            String url = urlBuilder
                    .append(TwitterApiURLs.END_POINT_URL)
                    .append(uri).toString();

            System.out.println("Request Url:"+url);

            TreeMap<String, String> oauthMap = OAuthMapFactory.getOAuthMap(keyMap.get("ck"),keyMap.get("ac"));

            String signature = OAuthSignatureFactory.makeSignature(method, url, data, oauthMap);
            String oAuthHeader = OAuthHeaderFactory.makeOAuthHeader(signature, oauthMap,keyMap.get("cks"),keyMap.get("ats"));
            String urlWithParam = OAuthParamFactory.makeURLwithParam(url, data);

            URL sendUrl = new URL(urlWithParam);
            return sendRequest(sendUrl,oAuthHeader,method);

        } catch (Exception e) {
            return e.toString();
        }
    }

    private String sendRequest(URL sendUrl,String oAuthHeader,String method) {

        try {

            HttpURLConnection urlConnection = (HttpURLConnection) sendUrl.openConnection();

            urlConnection.setRequestProperty("Authorization", oAuthHeader);
            urlConnection.setRequestMethod(method);
            urlConnection.connect();

            return HttpResponseHandler.receiveResponse(urlConnection);

        } catch (Exception e) {
            return e.toString();
        }
    }
}
