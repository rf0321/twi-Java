[![Build Status](https://travis-ci.org/ItinoseSan/twi-Java.svg?branch=0109)](https://travis-ci.org/ItinoseSan/twi-Java)
 [![LICENCE](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/ItinoseSan/Twi-Java/blob/0109/LICENCE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
# Twi-Java
Twitter API wrapper for Java. 

Main API wrapping is implemented by standard library. 

Json Decoding is implmented by org.json.
# Java Version
This wrapper's current java version is java8
# Installation
### 1.Download Twi-Java.jar 
### 2.Add as Library the jar
# Usage
Input your twitter api tokens
```java
 TwiJava twitter = new TwiJava();
         twitter.setConsumerKey("consumerkey");
         twitter.setConsumerSecretKey("consumerSecret");
         twitter.setAccessToken("accessToken");
         twitter.setAccessTokenSecret("accessTokenSecret");
```
Tweet
```java
twitter.tweet("Hello World");
```
Delete Tweet
```java
twitter.deleteTweet("your tweet id_str");
```
Search Tweet
```java
twitter.searchTweet("Hello");
```
Custom Search Tweet

If you use optional param
```java
        TreeMap<String,String> keyMap = new TreeMap<>();
        keyMap.put("ck","xxxxx");
        keyMap.put("cks","xxxxx");
        keyMap.put("ac","xxxxx");
        keyMap.put("ats","xxxxx");


        TreeMap<String,String> customSearch = new TreeMap<>();
        customSearch.put("q","Hello");
        customSearch.put("count","25");
        customSearch.put("locale","ja");
        customSearch.put("result_type","popular");

        TwitterAPIRequests apiRequests = new TwitterAPIRequests();
        apiRequests.searchStatus(customSearch,keyMap);
```
Get user Timeline
```java
twitter.getUserTimeLine(100); 
```
Get home Timeline
```java
twitter.getHomeTimeLine(100);
```

## Json decode(this is optional mini function)
```java
String json = twitter.getHomeTimeLine(50);
JsonDecoder.decode(json);
```

# Contributing
I welcome it. But if you pullrequest to this repository,you should write description of pullrequest content in English.
# Donation
Monacoin address:```MKFV3sckqr2o9fvFMX1SBhWcLP6jf75y8u```
# LICENCE
```
MIT License

Copyright (c) [2017] ItinoseSan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
# Ask me
If you have some question or advice and so on, Please contact my [Twitter](https://twitter.com/ItinoseVM) or open issue this repository. Im waiting;)
