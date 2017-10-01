![](https://img.shields.io/badge/language-java%208-orange.svg)  [![](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/ItinoseSan/Twi-Java/blob/master/TwitterAPI/LICENCE)

# Twi-Java
This is class-library when you handle TwitterAPI that build on Gradle

# The content relations pull request
I welcome it.but if you pullrequest to this repository,you should write description of pullrequest content in English.
# Installation
### 1.Clone this repository.
### 2.Twi-Java jar add as library your Java application
```build.gradle
dependencies {
    compile 'TwiJava1.0.0'
}
```
### 3.Please write this code when you want to use twitterAPI on Java application.
#### Tweet
```Java
         TwiJava twitter=new TwiJava.TwiJavaToken()
                .setConsumerKey("consumerkey")
                .setConsumerSecretKey("consumerkeysecret")
                .setAccessToken("accessToken")
                .setAccessTokenSecret("accessTokensecret")
                .buildTokens();
         twitter.tweet("Hello TwiJava World");
```  
#### Get UserTimeline
```Java
   twitter.getHomeTimeLine(100); //Integer is tweet count 
```
#### Get HomeTimeline
```Java
   twitter.getUserTimeLine(100); 
```



    
