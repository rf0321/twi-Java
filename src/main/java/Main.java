import twijava.TwiJava;


public class Main {
    public static void main(String[] args) throws Exception {
        TwiJava twitter=new TwiJava.TwiJavaToken()
                .setConsumerKey("consumerkey")
                .setConsumerSecretKey("consumerkeysecret");
                .setAccessToken("accessToken");
                .setAccessTokenSecret("accessTokensecret");
                .buildTokens();
        System.out.println(twitter.tweet("おはよう"));
        System.out.println(twitter.getHomeTimeline(5));
        System.out.println(twitter.getUserTimeline(5));
    }
}
