package twijava.core;
import twijava.net.TwiJavaHttpRequest;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class TwiJavaMainSystem {
    public static String consumerKey;
    public static String consumerKeySecret;
    public static String accessToken;
    public static String accessTokenSecret;



    TwiJavaHttpRequest httpRequest=new TwiJavaHttpRequest();
    public TwiJavaMainSystem(String consumerKey,String consumerKeySecret,String accessToken,String accessTokenSecret){
        this.consumerKey=consumerKey;
        this.consumerKeySecret=consumerKeySecret;
        this.accessToken=accessToken;
        this.accessTokenSecret=accessTokenSecret;
    }
    public String postcall(String text) throws IOException{
        Map<String,String>data=new TreeMap<>();
        data.put("status",text);
        data.put("trim_user","1");

        return httpRequest.post(data);
    }
    /*private String gethome(String tweetCounter){
        Map<String,String>timeLineData=new TreeMap<>();
        timeLineData.put("count",tweetCounter);
        timeLineData.put("trim_user","1");

        return httpRequest.get("GET",timeLineData);
    }
    private String getuser(String tweetCounter){
        Map<String,String>userTimeLineData=new TreeMap<>();
        userTimeLineData.put("count",tweetCounter);
        userTimeLineData.put("trim_user","1");

        return httpRequest.get("GET",userTimeLineData);
    }*/
}
