[![](http://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/ItinoseSan/Twi-Java/blob/master/TwitterAPI/LICENCE)
[![](https://img.shields.io/travis/ItinoseSan/Twi-Java.svg?style=flat-square)](https://travis-ci.org/ItinoseSan/Twi-Java)

# Twi-Java
Library for TwitterAPI ver java.This is one of the my practice to use WebAPI without famouse library
# How to Use
### 1.Clone this repository.
### 2.Apache HttpComponent add as a library your Java application project.
### 3.Please write this code when you want to tweet on Java application.
#### Tweet
```Java
 public static void main(String[] args) throws IOException {
        TwiJava twiJava = new TwiJava("ConsumerKey", "ConsumerSecret","AccessToken", "AccessTokenSecret");
        System.out.println(twiJava.tweet("Hello World from Twi-Java"));
    }
```  
#### GetUserTimeline
```Java
   System.out.println(twiJava.GetUserTimeLine("100")); //String is tweet count 
```
#### GetHomeTimeline
```Java
  System.out.println(twiJava.GetUserTimeLine("100")); 
```
# LICENCE
MIT

※Japanese

# Twi-Java
Javaで作ったTwitterAPIを使うライブラリです。
# 使い方
### 1.リポジトリをクローンします。
### 2.ApacheのHttpComponentをJavaプロジェクト内にライブラリとして追加します。
### 3.使用する場合は上の用なコードを書いてください。
# ライセンス
MITライセンスなので使用、改変ともに自由です。

    
