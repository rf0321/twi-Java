package twijava.core;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class AuthorizationHttpCore {

  /** public String connect(URL url, String requestJson,String method) throws Exception {
        HttpURLConnection
        connection = (HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method);
        connection.setRequestProperty("Authorization",requestJson);
        connection.connect();
        BufferedWriter writer = new BufferedWriter
                (new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));

        System.out.println("----- Request JSON -----");
        System.out.println(requestJson);
        writer.write(requestJson);
        writer.flush();
        System.out.println("----- Header -----");
        Map headers = connection.getHeaderFields();
        headers.keySet().stream().forEach((key) -> {System.out.println("[" + key + ":" + headers.get(key) + "]");});


        InputStreamReader isr = connection.getResponseCode() == HttpsURLConnection.HTTP_OK  ?
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8) :
                new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        String resultLine = "";
        System.out.println("----- Body -----");
        while((line = reader.readLine()) != null) {
            if(method.equals("GET")) {
                System.out.println(line);
            }
            resultLine = resultLine + line;
        }
        writer.close();
        reader.close();

        return resultLine;
    }*/
}
