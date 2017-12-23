package twijava.http;

import twijava.api.URLsUtil;
import twijava.oauth.OAuthParamFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;


public class HttpRequest {

    private static final String POST = "POST";
    private static final String GET = "GET";


    public String post(String requestUrl, String ck, String cks, String ac, String ats,
                          TreeMap<String, String> data) throws Exception {
        String url = URLsUtil.END_POINT_URL + requestUrl;

        return requestToAPI(POST,url,data,ck,cks,ac,ats);
    }

    public String get(String requestUrl,String ck,String cks,String ac,String ats,
                      TreeMap<String,String>data)throws Exception {
        String url = URLsUtil.END_POINT_URL + requestUrl;

        return requestToAPI(GET,url,data,ck,cks,ac,ats);
    }

    private String requestToAPI(String method, String url, TreeMap<String,String> data, String ck,
                                String cks, String ac, String ats) throws Exception{

        TreeMap<String, String> oauthMap = OAuthParamFactory.getOAuthMap(ck, ac);

        String signature = OAuthParamFactory.makeSignature(method, url, data, oauthMap);
        String oAuthHeader = OAuthParamFactory.makeOAuthHeader(signature, oauthMap, cks, ats);
        String urlwithParam = OAuthParamFactory.makeURLwithParam(url, data);

        URL sendurl = new URL(urlwithParam);

        HttpURLConnection urlConnection = (HttpURLConnection) sendurl.openConnection();

        urlConnection.setRequestProperty("Accept-Language", "ja");
        urlConnection.setRequestProperty("Authorization", oAuthHeader);
        urlConnection.setRequestMethod(method);
        urlConnection.connect();

        return receiveResponse(urlConnection,method);
    }

    private String receiveResponse(HttpURLConnection connection, String method) throws Exception{

        InputStreamReader isr = connection.getResponseCode() == HttpURLConnection.HTTP_OK ?
                new InputStreamReader(connection.getInputStream())
                : new InputStreamReader(connection.getErrorStream());

        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            // if request POST then, print out json.
            if (method.equals(POST))
                System.out.println(sb.append(line));
            else
                sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
