import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestHttpRequest
{
    public String post(String authheader,String url)throws IOException{
        URL urls=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)urls.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization",authheader);
        connection.connect();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
        writer.write(authheader);
        writer.flush();

        InputStreamReader isr = connection.getResponseCode() == HttpURLConnection.HTTP_OK  ? new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8) : new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        String resultLine = "";
        System.out.println("----- Body -----");
        while((line = reader.readLine()) != null) {
            System.out.println(line);
            resultLine = resultLine + line;
        }
        writer.close();
        reader.close();

        return resultLine;

    }
}
