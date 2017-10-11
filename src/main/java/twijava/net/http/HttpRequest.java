package twijava.net.http;

import twijava.net.oauth.OAuthUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class HttpRequest {

    private static final String TWITTERAPI_BASEURL = "https://api.twitter.com/1.1/"; //common url of endpoint

    private static final String POST="POST";

   public String request(String method,String uri,String ck,String ac,String cks,String ats,
                             TreeMap<String,String>data)throws Exception {

      String url = TWITTERAPI_BASEURL + uri;

      TreeMap<String, String> oauthMap = OAuthUtil.getOAuthParam(ck, ac);

      String signature = OAuthUtil.generateSignature(method, url, data, oauthMap);
      String oAuthHeader = OAuthUtil.makeOAuthHeader(signature, oauthMap, cks, ats);
      String urlwithParam = OAuthUtil.makeURLwithParam(url, data);

      URL sendurl = new URL(urlwithParam);

      HttpURLConnection urlConnection = (HttpURLConnection) sendurl.openConnection();

      if (method.equals(POST)) {
          urlConnection.setRequestMethod(POST);
      }

      urlConnection.setRequestProperty("Accept-Language", "ja");
      urlConnection.setRequestProperty("Authorization", oAuthHeader);
      urlConnection.connect();

      InputStreamReader isr = urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK ?
              new InputStreamReader(urlConnection.getInputStream())
              : new InputStreamReader(urlConnection.getErrorStream());

      BufferedReader reader = new BufferedReader(isr);
      StringBuilder sb=new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
          if (method.equals(POST))
              System.out.print(sb.append(line));
          else sb.append(line);
      }
      reader.close();
      return sb.toString();
  }
}
