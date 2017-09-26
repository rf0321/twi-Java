import twijava.TwiJava;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TwiJava twitter=new TwiJava.TwiJavaToken()
                .setConsumerKey("P0pYagYZV2DuEGgcxLTpREf62")
                .setConsumerSecretKey("rmt8HuTJ5N01DXMK1NT2XgNS5eoUXOPU0DNBcHeh7xaBX5zTCy")
                .setAccessToken("865875664074792960-2OYUu49otbCkov5yKH7K4FD4EbRYLEw")
                .setAccessTokenSecret("YFUr5FJ9hzc0drAnIsb21pdz8CGel7WjKgYuExvp6K2hj")
                .buildTokens();
        System.out.println(twitter.tweet("Hello World from refactored Twitter library"));
        System.out.println(twitter.getHomeTimeLine("5"));
        System.out.println(twitter.getUserTimeLine("5"));
    }
}
