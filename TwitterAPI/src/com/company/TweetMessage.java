package com.company;


public class TweetMessage implements TweetInterface{
    private String Message;

    public TweetMessage(String Message){
        this.Message=Message;
    }
    @Override
    public String sentence(){
        return Message;
    }
}
