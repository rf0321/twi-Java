package twijava.api;

import twijava.http.HttpRequest;
import java.util.TreeMap;

public class APIRequest {

    public String updateStatus(TreeMap<String,String> postData,
                                      String ck, String cks, String at, String ats){
        try {
            HttpRequest httpRequest = new HttpRequest();
            return httpRequest.post(URLsUtil.USER_UPDATESTATUS_URL, ck, cks, at, ats, postData);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String showStatus(String tlFlag,TreeMap<String,String> showData,
                                    String ck, String cks, String at, String ats){
        try{
            String url;
            HttpRequest httpRequest = new HttpRequest();

            if(tlFlag.equals("home")){
                url = URLsUtil.TIMELINE_URL;
            }
            else {
                url = URLsUtil.USER_TIMELINE_URL;
            }
            return httpRequest.get(url, ck, cks, at, ats, showData);
        }catch (Exception e){
            return e.toString();
        }

    }
}
