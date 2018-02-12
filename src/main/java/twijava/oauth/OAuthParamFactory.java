package twijava.oauth;
import java.util.*;

public class OAuthParamFactory {

    public static String makeURLwithParam(String url,TreeMap<String,String>paramMap){

        StringBuffer strBuffer=new StringBuffer(url);
        TreeMap<String,String>treeMap=new TreeMap<>();
        treeMap.putAll(paramMap);

        for (Map.Entry<String, String> paramEntry : treeMap.entrySet()) {
            if (paramEntry.equals(treeMap.firstEntry())) {
                strBuffer.append("?");
            } else {
                strBuffer.append("&");
            }
            strBuffer.append(paramEntry.getKey() + "=" + paramEntry.getValue());
        }

        return strBuffer.toString();
    }
}