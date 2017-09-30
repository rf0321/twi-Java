package twijava.json.objects;

public class UserTimeLines {
    /**
     * @param created_at tweet made by twitter user
     * @param id_str id string
     * @param text tweet content(text)
     * @param id
     * @param retweet_count
     * @param user
     */
    public String created_at;
    public String id_str;
    public String text;
    public String retweet_count;
    public TimeLineUser user;
    public UserTimeLines(String created_at,String id_str,String text,String retweet_count,TimeLineUser user){
        this.created_at=created_at;
        this.id_str=id_str;
        this.retweet_count=retweet_count;
        this.text=text;
        this.user=user;
    }
}
