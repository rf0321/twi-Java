package twijava.net.core;
import  twijava.net.data.surpport.NameValuePairs;
import  java.net.HttpURLConnection;
import  java.net.URL;
import  java.io.*;
import  java.util.List;
import  java.lang.Exception;

public class HttpRequest {
    public String post(String url, List<NameValuePairs>data, String header) throws  Exception {
        HttpURLConnection connection = null;
        try {
            URL sendurl = new URL(url);
            connection = (HttpURLConnection) sendurl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", header);
            connection.connect();

            OutputStream stream = connection.getOutputStream();

            PrintStream ps = new PrintStream(stream);
            ps.print(data.toString());
            ps.close();
            System.out.println("----- Request JSON -----");
            System.out.println(data.toString());

        }catch (Exception e){
            throw new Exception("You wrong http connection");
        }

        /* for debugging

        Map headers = connection.getHeaderFields();
        headers.keySet().stream().forEach((key) -> {
            System.out.println("[" + key + ":" + headers.get(key) + "]");
        });
        */
       /*InputStreamReader isr = connection.getResponseCode() == HttpsURLConnection.HTTP_OK  ?
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8) :
                new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);*/

       return data.toString();
    }
}
