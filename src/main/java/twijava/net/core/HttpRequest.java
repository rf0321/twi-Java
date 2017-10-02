package twijava.net.core;
import java.net.HttpURLConnection;
import  java.net.URL;
import  java.net.URLConnection;
import  java.io.*;
import  java.util.Map;
import  twijava.net.APIRequestSupporter;
import static java.lang.System.in;


public class HttpRequest {

   public String get(String uri, Map<String,String>data,String authSiganature) throws Exception{
       APIRequestSupporter api=new APIRequestSupporter();

       uri+="?"+api.formUrlEncodedContent(data);

       URLConnection connection=new URL(uri).openConnection();
       connection.setRequestProperty("Authorization",authSiganature);
       connection.connect();

       BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
       return convertBody(br);
   }
   public String post(String uri,Map<String,String>data,String authSignature)throws Exception{
       APIRequestSupporter api=new APIRequestSupporter();
       uri+=api.formUrlEncodedContent(data);
       URL sendurl=new URL(uri);
       HttpURLConnection connection;
       connection=(HttpURLConnection)sendurl.openConnection();
       connection.setRequestMethod("POST");
       connection.setRequestProperty("Authorization", authSignature);
       BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
       return convertBody(br);
   }
    private String convertBody(BufferedReader bufferedReader) throws Exception{
       StringBuffer sb = new StringBuffer();
       String st;
       while((st = bufferedReader.readLine()) != null) sb.append(st);
       try { in.close(); }  catch(Exception e) { e.printStackTrace(); }
       return sb.toString();
   }
}
