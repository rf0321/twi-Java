package twijava.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ParamEncoder {

    public static String encode(String params){
        try{
            return URLEncoder.encode(params,"UTF-8").replace("+","%20");
        }
        catch (UnsupportedEncodingException e){
            return e.toString();
        }
    }
}
