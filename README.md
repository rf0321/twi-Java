[![Build Status](https://travis-ci.org/ItinoseSan/twi-Java.svg?branch=release)](https://travis-ci.org/ItinoseSan/twi-Java)
![lang-type](https://img.shields.io/badge/languauge-java%208-yellow.svg) [![LICENCE](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/ItinoseSan/Twi-Java/blob/release/LICENCE)

# Twi-Java
Twitter API wrapper for Java.Basic system is used by standard library only. Parsing json is used by org.json
# Installation
### 1.Download Twi-Java.jar 
### 2.Add as Library the jar
#### Gradle
```build.gradle
dependencies {
    compile 'twi-java1.0.2'
}
```
#### Standard version(IntelliJ,AndroidStudio)
Right click jar->Select "Add as Lbrary"
# How to use
Input your twitter api tokens
```java
 TwiJava twitter = new TwiJava.TokenInitialize()
                .setConsumerKey("Your TwitterAPI ConsumerKey")
                .setConsumerSecretKey("Your TwitterAPI ConsumerKeySecret")
                .setAccessToken("Your TwitterAPI AccessToken")
                .setAccessTokenSecret("Your TwitterAPI AccessTokenSecret")
                .buildTokens();
```
Tweet
```java
twitter.tweet("Hello World");
```
Get user Timeline
```java
twitter.getUserTimeLine(100); //Integer is tweet count of timeline.You can change this
```
Get home Timeline
```java
twitter.getHomeTimeLine(100);
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
# If you have some question or advice and so on
Please contact my [Twitter](https://twitter.com/ItinoseVM) or open issue this repository. Im waiting;)
