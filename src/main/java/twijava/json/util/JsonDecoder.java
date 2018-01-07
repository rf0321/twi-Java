package twijava.json.util;

import  org.json.JSONArray;
import org.json.JSONException;
import  twijava.json.objects.TwitterJsonObjects;

import java.util.stream.IntStream;



public class JsonDecoder {

    public static void decode(String responejson) {
        TwitterJsonObjects objects=new TwitterJsonObjects();
        try{
            JSONArray jsonArray=new JSONArray(responejson);
            IntStream.range(0, jsonArray.length())
                    .mapToObj(i -> jsonArray.getJSONObject(i))
                    .forEach(i -> System.out.println(
                            "Posted:"+i.getString(objects.created_at)+"\n"+
                                    "User objects:"+ i.getJSONObject(objects.user)+"\n"+
                                    "Tweet content:"+i.getString(objects.text)+"\n"));
        }
        catch (JSONException e){
            String exceptionMessage = e.toString();
            System.out.println("Parse Exception: "+ exceptionMessage);
        }
    }
}

                /*for(int i=0; i<jsonArray.length();i++) {
                JSONObject object = jsonArray.getJSONObject(i); //mapToObj
                System.out.println("Timeline count:"+i); //point of forEach
                System.out.println("Posted:"+object.getString(objects.created_at));
                System.out.println("user object content:"+object.getJSONObject(objects.user));
                System.out.println("Tweet content:"+object.getString(objects.text));
                System.out.println();
                }*/