package twijava.json.util;
import  twijava.json.objects.HomeTimeLines;
import twijava.json.objects.TimeLineUser;
import  twijava.json.objects.UserTimeLines;

public class TwiJavaJsonDecoder {
    private String created_at="[";
    private String id_str;
    private String text="\"text:\"";
    private String retweet_count;
    private TimeLineUser user;


    public String decodeHjson(String responejson) throws Exception{
        if(responejson.startsWith(created_at)){
           //なんか処理
        }
        return responejson;
    }
    public String decodeUjson(String responejson){
          return responejson;
    }
}
