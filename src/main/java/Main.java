import twijava.TwiJava;

public class Main {
    public static void main(String[] args) throws Exception {
        TwiJava twitter = new TwiJava.SetAPIToken()
                .setConsumerKey("ck")
                .setConsumerSecretKey("cks")
                .setAccessToken("at")
                .setAccessTokenSecret("ats")
                .buildTokens();
        twitter.tweet("test");
    }
}
