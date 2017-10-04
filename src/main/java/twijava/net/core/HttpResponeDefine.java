package twijava.net.core;

import java.util.Collections;
import java.util.Map;

public class HttpResponeDefine {
    private int statusCode=0;
    private Map<String,String>resHeader= Collections.emptyMap();
    private String responeBody=null;

    public int setStatus(int code){
        return this.statusCode=code;
    }
    public Map<String,String>setResHeader(Map<String,String>Header){
        return this.resHeader=Header;
    }
    public String setResponeBody(String responeBody){
        return this.responeBody=responeBody;
    }
    int getStatusCode=this.statusCode;

    Map<String,String>getResHeader=this.resHeader;

    String getResponeBody=this.responeBody;
}
