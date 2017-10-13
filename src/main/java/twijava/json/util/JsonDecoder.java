package twijava.json.util;
import  org.json.JSONArray;
import  org.json.JSONObject;
import  twijava.json.objects.TwitterJsonObjects;

import java.util.stream.IntStream;


public class JsonDecoder { //Decoding json class.This opensource uses "org.json" on parsing json.
     /*
       This is decoding json class.Using lib is org.json
     */
    public static void timelinedecode(String responejson){
        TwitterJsonObjects objects=new TwitterJsonObjects();
        try{
            JSONArray jsonArray=new JSONArray(responejson);
            IntStream.range(0, jsonArray.length())
                    .mapToObj(i -> jsonArray.getJSONObject(i))
                    .forEach(i -> System.out.println("Timeline count:" + jsonArray.length()+"\n"+
                            "Posted:"+i.getString(objects.created_at)+"\n"+
                            "User objects:"+ i.getJSONObject(objects.user)+"\n"+
                            "Tweet content:"+i.getString(objects.text)+"\n"));
        }
        catch (Exception e){
        }
    }
}

                /*for(int i=0; i<jsonArray.length();i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                System.out.println("Timeline count:"+i);

                System.out.println("Posted:"+object.getString(objects.created_at));

                System.out.println("user object content:"+object.getJSONObject(objects.user));

                System.out.println("Tweet content:"+object.getString(objects.text));


                System.out.println();
                }*/