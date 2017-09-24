import twijava.TwiJava;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TwiJava twitter = new TwiJava();
        twitter.setConsumerKey("pCRUJjuqLUg0f6auQ94jXj0wo");
        twitter.setConsumerSecret("1pl0DjAQHSUDyitbRma44TTKltMH29N3XUIbHxvnFiNfWhTMaq");
        twitter.setAccessToken("865875664074792960-2OYUu49otbCkov5yKH7K4FD4EbRYLEw");
        twitter.setAccessTokenSecret("YFUr5FJ9hzc0drAnIsb21pdz8CGel7WjKgYuExvp6K2hj");

        twitter.tweet("Hello World");
    }
}
