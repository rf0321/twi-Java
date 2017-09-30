package twijava.core;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class AuthorizationHttpCore {

    public String connect(URL url,String method,String postdata,String header) throws Exception {
        URL testAPI = new URL(url + postdata);
        HttpURLConnection
                connection = (HttpURLConnection) testAPI.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(method);
        connection.setRequestProperty("Authrization", header);
        connection.connect();
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        String response;
        while ((response = reader.readLine()) != null) {
            System.out.println(response);
        }
        return response;
    }
}
