package twijava;


public class TwitterRequests {
    /**
     * @param request instances
     */
    private Tweet tweet = new Tweet();

    private DeleteTweet deleteTweet = new DeleteTweet();

    private HomeTimeLine home = new HomeTimeLine();

    private UserTimeLine user = new UserTimeLine();

    private UserProfile profile = new UserProfile();

    private FollowerList follower = new FollowerList();

    private FriendList friend = new FriendList();

    private SearchTweet search = new SearchTweet();

    public void tweet(String text){
        tweet.tweetRequest(text);
    }

    public void deleteTweet(String idStr){
        deleteTweet.deleteRequest(idStr);
    }

    public String getUserProfile(String screen_name) {
        return profile.getProfileRequest(screen_name);
    }

    public String getFollowerList(){
        return follower.getFollowerRequest();
    }

    public String getFriendList(){
        return friend.getFriendRequest();
    }

    public String searchTweet(String query){
        return search.searchRequest(query);
    }

    public String getHomeTimeLine(int count){
        return home.getTimeLineRequest(count);
    }

    public String getUserTimeLine(int count){
        return user.getTimeLineRequest(count);
    }
}