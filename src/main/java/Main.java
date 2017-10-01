import twijava.TwiJava;

public class Main {
    public static void main(String[] args) throws Exception {
        TwiJava twitter = new TwiJava.SetAPIToken()
                .setConsumerKey("ck")
                .setConsumerSecretKey("cks")
                .setAccessToken("at")
                .setAccessTokenSecret("ats")
                .buildTokens();
        twitter.getHomeTimeLine(100,false);
        twitter.getHomeTimeLine(100,true);
        twitter.getUserTimeLine(100,false);
        twitter.getUserTimeLine(100,true);
    }
}
