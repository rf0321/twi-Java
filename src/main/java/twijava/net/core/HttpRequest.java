package twijava.net.core;

import twijava.net.OAuthUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeMap;

public class HttpRequest {
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";

    private static final String UTF_8="UTF-8";

    private static final String POST_METHOD_NAME="POST";
    private static final String GET_METHOD_NAME="GET";

    public String getRequest(String uri,String ck,String ac,String cks,String ats,
                             TreeMap<String,String>data)throws Exception{

        String url=TWITTERAPI_BASEURL+uri;
        TreeMap<String,String>oauthMap=OAuthUtil.getOAuthParam(ck,ac);

        String signature=OAuthUtil.generateSignature(GET_METHOD_NAME,url,data,oauthMap);
        String oauthSignature=OAuthUtil.makeOAuthHeader(signature,oauthMap,cks,ats);
        String urlwithParam=OAuthUtil.getURLWithParam(url,data);

        URL sendurl=new URL(urlwithParam);

        URLConnection urlConnection = .openConnection();
        urlConnection.setRequestProperty("Authorization", authHeader);

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }

}
