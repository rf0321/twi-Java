package twijava.net.core;

import twijava.net.OAuthUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class HttpRequest {
    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/";

    private static final String UTF_8="UTF-8";

    private static final String POST_METHOD_NAME="POST";
    private static final String GET_METHOD_NAME="GET";

    public String post(String uri,String ck,String ac,String cks,String ats,TreeMap<String,String>data)throws Exception{

        String URL=TWITTERAPI_BASEURL+uri;

        TreeMap<String,String> oauthMap= OAuthUtil.getOAuthParam(ck,ac);

        String signature=OAuthUtil.generateSignature(POST_METHOD_NAME,URL,data,oauthMap);
        String oauthSignature=OAuthUtil.makeOAuthHeader(signature,oauthMap,cks,ats);
        String urlWithParam=OAuthUtil.getURLWithParam(URL,data);

        URL sendurl=new URL(urlWithParam);

        HttpURLConnection connection=(HttpURLConnection)sendurl.openConnection();

        connection.setRequestMethod(POST_METHOD_NAME);
        connection.setRequestProperty("Authorization",oauthSignature);
        connection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return convertBody(br);
    }

    private String convertBody(BufferedReader br)throws Exception{
        String respone=null;
        while (br.readLine()!=null){
            respone=String.format("\n")+"\n";
        }
        return respone;
    }

}
