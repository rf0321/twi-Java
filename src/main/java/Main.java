import twiJavaEngine.TwiJava;
import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        TwiJava twitter = new TwiJava("39PLQU2FQhR8vaDxuGo8Y3v5P", "3bm3JaTusFtpwv1R8Yxxh4ko6OkCLeGKOcgbsNvQSnfumFBVBz",
                "865875664074792960-2OYUu49otbCkov5yKH7K4FD4EbRYLEw", "YFUr5FJ9hzc0drAnIsb21pdz8CGel7WjKgYuExvp6K2hj");

        System.out.println(twitter.tweet("Hello World"));
        System.out.println(twitter.getHomeTimeLine("5"));
        System.out.println(twitter.getUserTimeLine("5"));
    }
}
