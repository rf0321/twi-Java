package com.company;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import sun.net.www.http.HttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

public class UpdateJson  {
    private String CONSUMER_KEY;
    private String CONSUMER_KEY_SECRET;
    private String ACCESSTOKEN;
    private String ACCESSSECRET;

    HttpClient httpClient;
    static final String TwitterApiBaseUrl = "https://api.twitter.com/1.1/";
    public Timestamp timestamp;

    byte[]signatureHash;

    TwiAPIGetToken GetToken=new TwiAPIGetToken();

    public UpdateJson(String CONSUMER_KEY,String CONSUMER_KEY_SECRET,String ACCESSTOKEN,String ACCESSSECRET){
        this.CONSUMER_KEY=CONSUMER_KEY;
        this.CONSUMER_KEY_SECRET=CONSUMER_KEY_SECRET;
        this.ACCESSTOKEN=ACCESSTOKEN;
        this.ACCESSSECRET=ACCESSSECRET;

        try {
            signatureHash = CONSUMER_KEY_SECRET.getBytes("US-ASCII");
            signatureHash =ACCESSSECRET.getBytes("US-ASCII");
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
  public String tweet(String Text)throws UnsupportedEncodingException {
      Map<String,String>data=new Hashtable<>();
      data.put("status",Text);
      data.put("trim_user","1");
      String formData="";
      return SendRequest("statuses/update.json", data,formData);
     }
  public String SendRequest(String URL,Map<String,String>data,String Formdata)throws UnsupportedEncodingException {
      String full_url=TwitterApiBaseUrl+URL;
      TwiAPIGetToken twiAPIGetToken=new TwiAPIGetToken();

      data.put("oauth_consumer_key", twiAPIGetToken.getCONSUMER_KEY());
      data.put("oauth_signature_method", "HMAC-SHA1");
      data.put("oauth_timestamp", timestamp.toString());
      data.put("oauth_nonce", GenerateNonce());
      data.put("oauth_token", twiAPIGetToken.getACCESSTOKEN());
      data.put("oauth_version", "1.0");
      data.put("oauth_signature", GenerateSignature(full_url, data));

      String oAuthHeader=GenerateOAuthHeader(data);
      String key="oauth_";
      String EncodeFormat="ASCII";

      String formData=URLEncoder.encode(key,EncodeFormat);
      return SendRequest(full_url,data,formData);
  }
  String GenerateSignature(String URL,Map<String,String>data)throws UnsupportedEncodingException{
      String signature=String.join("&" ,String.format("{0}={1}",URL));
      signature.format("{0}&{1}&{2}","POST",URL);
      String key="oauth_";
      String EncodeFormat="ASCII";
      return URLEncoder.encode(key,EncodeFormat);
  }
  String GenerateOAuthHeader(Map<String,String> data){
    data.put("{0}=\"{1}\"","");
    return "OAuth"+data+String.join(",");
  }
  public String SendRequest(String full_url,String oAuthHeader,String formData)throws UnsupportedEncodingException{

      try{
          
      }
      catch (Exception e){
        e.printStackTrace();

      }
      String respone;
      return GenerateNonce();
  }
    static String GenerateNonce(){
        Random rnd =new Random();
        int val;
        val=rnd.nextInt(123400);
        return rnd.toString();
    }
}
