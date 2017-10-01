import twijava.TwiJava;

/**
 * using library examples
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TwiJava twitter=new TwiJava.TwiJavaToken()
                .setConsumerKey("")
                .setConsumerSecretKey("")
                .setAccessToken("")
                .setAccessTokenSecret("")
                .buildTokens();
        twitter.tweet("");
        twitter.getHomeTimeLine(100);
        twitter.getUserTimeLine(100);
    }
}
