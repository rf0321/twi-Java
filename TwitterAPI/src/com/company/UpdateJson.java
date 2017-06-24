package com.company;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.*;

public class UpdateJson  {
    private String CONSUMER_KEY;
    private String CONSUMER_KEY_SECRET;
    private String ACCESSTOKEN;
    private String ACCESSSECRET;

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
  public String tweet(String Text) {
      Map<String,String>data=new Hashtable<>();
      data.put("status",Text);
      data.put("trim_user","1");
      return SendRequest("statuses/update.json", data);
     }
  public String SendRequest(String URL,Map<String,String>data){
      String full_url=TwitterApiBaseUrl+URL;
      TwiAPIGetToken twiAPIGetToken=new TwiAPIGetToken();

      data.put("oauth_consumer_key", twiAPIGetToken.getCONSUMER_KEY());
      data.put("oauth_signature_method", "HMAC-SHA1");
      data.put("oauth_timestamp", timestamp.toString());
      data.put("oauth_nonce", GenerateNonce());
      data.put("oauth_token", twiAPIGetToken.getACCESSTOKEN());
      data.put("oauth_version", "1.0");
      data.put("oauth_signature", GenerateSignature(full_url, data));

      return SendRequest(full_url,data,formData);
  }
  String GenerateSignature(String URL,Map<String,String>data){
      String signature=String.join("&" );
      signature.format("{0}={1}");
      
  }
    static String GenerateNonce(){
        Random rnd =new Random();
        int val;
        val=rnd.nextInt(123400);
        return rnd.toString();
    }
}
