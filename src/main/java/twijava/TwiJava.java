/**
 * TwiJava class
 *<p>This is client class</p>
 */
package twijava;

public class TwiJava{

    private TwitterRequests requests = new TwitterRequests();

    private APIKeyFactory keyFactory = new APIKeyFactory();

    public void authorize(String ck,String cks,String ac,String ats){
        keyFactory.buildKey(ck, cks, ac, ats);
    }

    public void tweet(String text){
        requests.tweet(text);
    }

    public void deleteTweet(String idStr){
        requests.deleteTweet(idStr);
    }

    public String searchTweet(String query){
        return requests.searchTweet(query);
    }

    public String getUserProfile(String screen_name){
        return requests.getUserProfile(screen_name);
    }

    public String getFollowerList(){
        return requests.getFollowerList();
    }

    public String getFriendList(){
        return requests.getFriendList();
    }

    public String getHomeTimeLine(int count){
        return requests.getHomeTimeLine(count);
    }

    public String getUserTimeLine(int count){
        return requests.getUserTimeLine(count);
    }
}
