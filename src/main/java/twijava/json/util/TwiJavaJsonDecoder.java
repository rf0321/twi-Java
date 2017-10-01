package twijava.json.util;
import  org.json.JSONArray;
import  org.json.JSONObject;
import  twijava.json.objects.TwitterJsonObjects;



public class TwiJavaJsonDecoder {

    public void decode(String responejson){
        TwitterJsonObjects objects=new TwitterJsonObjects();
        try{
                JSONArray jsonArray=new JSONArray(responejson);
                for(int i=0; i<jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(100);
                    System.out.println(i);
                    System.out.println(object.getString(objects.created_at));
                    System.out.println(object.getString(objects.screen_name));
                    System.out.println(object.getString(objects.name));
                    System.out.println(object.getString(objects.id));
                    System.out.println(object.getString(objects.text));
                    System.out.println();
                }
        }
        catch (Exception e){
        }
    }
}
