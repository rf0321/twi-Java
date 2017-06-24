package com.company;
import java.sql.Timestamp;
import java.util.*;


public class TwiAPIGetToken
{
    private String CONSUMER_KEY;
    private String CONSUMER_KEY_SECRET;
    private String ACCESSTOKEN;
    private String ACCESSSECRET;

    public String getCONSUMER_KEY(){
        return CONSUMER_KEY;
    }
    public String getCONSUMER_KEY_SECRET(){
        return CONSUMER_KEY_SECRET;
    }
    public String getACCESSTOKEN(){
        return ACCESSTOKEN;
    }
    public String getACCESSSECRET(){
        return ACCESSSECRET;
    }
    static String GenerateNonce(){
        Random rnd =new Random();
        int val;
        val=rnd.nextInt(123400);
        return rnd.toString();
    }
}
