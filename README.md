[![](http://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/ItinoseSan/Twi-Java/blob/master/TwitterAPI/LICENCE)

# Twi-Java
Simple post method library for TwitterAPI ver java
# How to Use
### 1.Clone this repository.
### 2.Apache HttpComponent add as a library your Java application project.
### 3.Please write this code when you want to tweet on Java application.

```Java
 public static void main(String[] args) throws IOException {
        UpdateJson updateJson = new UpdateJson("ConsumerKey", "ConsumerSecret","AccessToken", "AccessTokenSecret");
        System.out.println(updateJson.tweet("Hello World from Twi-Java"));
    }
```    
# LICENCE
MIT
    
