package twijava.json.util;
import  org.json.JSONArray;
import  org.json.JSONObject;
import  twijava.json.objects.TwitterJsonObjects;


public class JsonDecoder { //Decoding json class.This opensource uses "org.json" on parsing json.
     /*
       This is decoding json class.Using lib is org.json
     */
    public static void timelinedecode(String responejson){
        TwitterJsonObjects objects=new TwitterJsonObjects();
        try{
            JSONArray jsonArray=new JSONArray(responejson);
            for(int i=0; i<jsonArray.length();i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                System.out.println("Timeline count:"+i);

                System.out.println("Posted:"+object.getString(objects.created_at));

                System.out.println("user object content:"+object.getJSONObject(objects.user));

                System.out.println("Tweet content:"+object.getString(objects.text));


                System.out.println();
            }
        }
        catch (Exception e){
        }
    }
}
