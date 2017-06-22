package com.company;
import java.io.UnsupportedEncodingException;
import com.sun.crypto.provider.HmacSHA1;
import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TwitterAPI
{
    private String CONSUMER_KEY;
    private String CONSUMER_KEY_SECRET;
    private String ACCESSTOKEN;
    private String ACCESSSECRET;

    static final String TwitterAPIURL="https://api.twitter.com/1.1/";

    private Timestamp ts;

    byte[]signatureHash;

    public TwitterAPI(String CONSUMER_KEY,String CONSUMER_KEY_SECRET,String ACCESSSTOKEN,String ACCESSSECRET){
        this.CONSUMER_KEY=CONSUMER_KEY;
        this.CONSUMER_KEY_SECRET=CONSUMER_KEY_SECRET;
        this.ACCESSTOKEN=ACCESSSTOKEN;
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
    public String Tweet(String Message){
        Map<String,String>data=new HashMap<String,String>();
        data.put("status",Message);
        data.put("trim_user","1");
        return send_Request("statuses/update.json",data);
    }
    public String send_Request(String URL,Map<String,String>data){
        String full_URL=TwitterAPIURL+URL;
  /// timestamp^^
        data.put("oauth_consumer_key", CONSUMER_KEY);
        data.put("oauth_signature_method", "HMAC-SHA1");
        data.put("oauth_timestamp", timestamp.toString());
        data.put("oauth_nonce", GenerateNonce());
        data.put("oauth_token", ACCESSTOKEN);
        data.put("oauth_version", "1.0");
        data.put("oauth_signature", GenerateSignature(full_URL, data));

        String OAuthHEADER=GenHeader(data);

        return send_Request2(full_URL,data,OAuthHEADER);
    }
    static String GenerateNonce(){
        Random rnd =new Random();
        int val;
        val=rnd.nextInt(123400);
        return rnd.toString();
    }
}
