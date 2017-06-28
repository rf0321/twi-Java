[![](http://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/ItinoseSan/Twi-Java/blob/master/TwitterAPI/LICENCE)
[![](https://img.shields.io/travis/ItinoseSan/Twi-Java.svg?style=flat-square)](https://travis-ci.org/ItinoseSan/Twi-Java)

# Twi-Java
Simple post method library for TwitterAPI ver java
# How to Use
### 1.Clone this repository.
### 2.Apache HttpComponent add as a library your Java application project.
### 3.Please write this code when you want to tweet on Java application.
Post Tweet
```Java
 public static void main(String[] args) throws IOException {
        UpdateJson updateJson = new UpdateJson("ConsumerKey", "ConsumerSecret","AccessToken", "AccessTokenSecret");
        System.out.println(updateJson.tweet("Hello World from Twi-Java"));
    }
```    
# LICENCE
MIT

※Japanese

# Twi-Java
シンプルなTwitterポスト機能だけを作ったミニライブラリです。
# 使い方
### 1.リポジトリをクローンします
### 2.ApacheのHttpComponentをJavaプロジェクト内にライブラリとして追加します
### 3.ツイートする場合は上の用なコードを書いてください。
# ライセンス
MITライセンスなので使用、改変ともに自由です。

    
