package twijava.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponseHandler {

    public static String receiveResponse(HttpURLConnection connection) throws Exception {

        InputStreamReader isr = connection.getResponseCode() == HttpURLConnection.HTTP_OK ?
                new InputStreamReader(connection.getInputStream())
                : new InputStreamReader(connection.getErrorStream());

        BufferedReader reader = new BufferedReader(isr);
        StringBuilder responseJson = new StringBuilder();

        return handleResponse(connection,reader, responseJson);
    }

    private static String handleResponse(HttpURLConnection connection, BufferedReader reader,
                                         StringBuilder json) throws Exception{
        String line;

        int code = connection.getResponseCode();
        while ((line = reader.readLine()) != null) {
            if(code == HttpURLConnection.HTTP_OK) {
                System.out.println("HttpRequest accepted");
            }
            System.out.println("Response json");
            System.out.println(json.append(line)+"\n");
        }
        reader.close();

        return json.toString();
    }
}
