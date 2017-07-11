import TwiJavaMainFunction.TwiJava;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TwiJava twitter = new TwiJava("ConsumerKey", "ConsumerSecret","AccessToken", "AccessTokenSecret");
        System.out.println(twitter.tweet("Hello World"));
        System.out.println(twitter.getHomeTimeLine("5"));
        System.out.println(twitter.getUserTimeLine("5"));
    }
}
