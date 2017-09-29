![](https://img.shields.io/badge/language-java%208-orange.svg)  [![](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/ItinoseSan/Twi-Java/blob/master/TwitterAPI/LICENCE)

# Twi-Java
This is class-library when you handle TwitterAPI that build on Gradle

# The content relations pull request
I welcome it.but if you pullrequest to this repository,you should write description of pullrequest content in English.
# How to Use
### 1.Clone this repository.
### 2.Apache HttpComponent add as a library your Java application project.
```build.gradle
dependencies {
    compile 'org.apache.httpcomponents:httpclient:4.5.3'
    //You should use new library version
}
```
### 3.Please write this code when you want to use twitterAPI on Java application.
#### Tweet
```Java
         TwiJava twitter=new TwiJava.TwiJavaToken()
                .setConsumerKey("consumerkey")
                .setConsumerSecretKey("consumerkeysecret");
                .setAccessToken("accessToken");
                .setAccessTokenSecret("accessTokensecret");
                .buildTokens();
         twitter.tweet("Hello TwiJava World");
```  
#### Get UserTimeline
```Java
   System.out.println(twitter.getUserTimeLine(100)); //Integer is tweet count 
```
#### Get HomeTimeline
```Java
  System.out.println(twitter.getUserTimeLine(100)); 
```

※Japanese

# Twi-Java
Javaで作ったTwitterAPIを使うライブラリです。ビルドツールとしてGradleを導入してみました。
# 使い方
### 1.リポジトリをクローンします。
### 2.ApacheのHttpComponentをJavaプロジェクト内にライブラリとして追加します。
### 3.使用する場合は上の用なコードを書いてください。
# ライセンス
MITライセンスなので使用、改変ともに自由です。

    
