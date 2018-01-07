package twijava.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponseHandler {

    public static void getStatusMessage(HttpURLConnection connection) throws Exception{

        int httpStatusCode = connection.getResponseCode();

        if(httpStatusCode == HttpURLConnection.HTTP_CREATED){
            System.out.println("HttpStatusMessage: code201 POST Request Succeeded");
        }
        else if(httpStatusCode == HttpURLConnection.HTTP_ACCEPTED){
            System.out.println("HttpStatusMessage: code200 GET Requst Succeeded");
            System.out.println("===============================================");
        }
    }

    public static String receiveResponse(HttpURLConnection connection) throws Exception{

        InputStreamReader isr = connection.getResponseCode() == HttpURLConnection.HTTP_OK ?
                new InputStreamReader(connection.getInputStream())
                : new InputStreamReader(connection.getErrorStream());

        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(sb.append(line));
        }
        reader.close();
        return sb.toString();
    }
}
