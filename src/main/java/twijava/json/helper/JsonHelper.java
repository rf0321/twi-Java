package twijava.json.helper;

/*
this is test code
 */
public class JsonHelper {
    public  String arrayToObj(String json){
       if(json.startsWith("[")){
           json="{\"items\":" + json + "}";
           return json;
       }
       else {
           return json;
       }
    }
}
