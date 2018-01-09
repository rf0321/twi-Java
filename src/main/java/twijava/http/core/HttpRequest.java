package twijava.http.core;

import twijava.http.HttpResponseHandler;
import twijava.oauth.OAuthHeaderFactory;
import twijava.oauth.OAuthMapFactory;
import twijava.oauth.OAuthParamFactory;
import twijava.oauth.OAuthSignatureFactory;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.TreeMap;


public class HttpRequest {

    public String requestToAPI(String method, String url, TreeMap<String,String>data,List<String>keylist){

        try {

            TreeMap<String, String> oauthMap = OAuthMapFactory.getOAuthMap(keylist.get(0), keylist.get(2));

            String signature = OAuthSignatureFactory.makeSignature(method, url, data, oauthMap);
            String oAuthHeader = OAuthHeaderFactory.makeOAuthHeader(signature, oauthMap, keylist.get(1), keylist.get(3));
            String urlWithParam = OAuthParamFactory.makeURLwithParam(url, data);

            URL sendUrl = new URL(urlWithParam);

            HttpURLConnection urlConnection = (HttpURLConnection) sendUrl.openConnection();

            urlConnection.setRequestProperty("Authorization", oAuthHeader);
            urlConnection.setRequestMethod(method);
            urlConnection.connect();

            return HttpResponseHandler.receiveResponse(urlConnection);

        }catch (Exception e){

            return e.toString();
        }
    }
}