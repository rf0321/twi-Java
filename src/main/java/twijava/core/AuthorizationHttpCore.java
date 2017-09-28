package twijava.core;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class AuthorizationHttpCore { /**
                                     Json詰め込みができているか怪しいのでマスターブランチのTwiJavaでこのクラスを試す
                                     testディレクトリで書くこと。
                                     **/
    public String httpConnection(URL APIjson, String header, String methodname,
                                 Map<String,String> data)throws Exception {

        HttpURLConnection connection = null;
        connection = (HttpURLConnection) APIjson.openConnection();

        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(methodname);
        connection.setRequestProperty("Authorization", header);
        connection.connect();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),
                StandardCharsets.UTF_8));

        writer.write(APIjson.toString());
        writer.write(data.toString());

        writer.flush();

        InputStreamReader
                isr = connection.getResponseCode() == HttpsURLConnection.HTTP_OK ?
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8) :
                new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        String resultLine = "";


        while ((line = reader.readLine()) != null) {
            resultLine = resultLine + line;
        }
        writer.close();
        reader.close();

        return resultLine; //resultlineがJsonResponeになる
    }
}
