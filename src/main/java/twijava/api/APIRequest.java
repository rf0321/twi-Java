package twijava.api;

import twijava.http.HttpRequest;

import java.util.List;
import java.util.TreeMap;

public class APIRequest {

    public String updateStatus(TreeMap<String,String> postData, List<String>keylist){
        try {
            HttpRequest httpRequest = new HttpRequest();
            return httpRequest.post(URLsUtil.USER_UPDATESTATUS_URL, keylist, postData);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String showStatus(String tlFlag,TreeMap<String,String> showData, List<String>keylist){
        try{
            String url;
            HttpRequest httpRequest = new HttpRequest();

            if(tlFlag.equals("home")){
                url = URLsUtil.TIMELINE_URL;
            }
            else {
                url = URLsUtil.USER_TIMELINE_URL;
            }
            return httpRequest.get(url, keylist, showData);
        }catch (Exception e){
            return e.toString();
        }
    }
}