package twijava.http;


public interface HttpRequestComponents {
    // Http Request Method
    String getMethod();

    // get instance http request
    HttpRequest http();
}
