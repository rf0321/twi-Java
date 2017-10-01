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
# LICENCE
```
MIT License

Copyright (c) [2017] [ItinoseSan]

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




    
