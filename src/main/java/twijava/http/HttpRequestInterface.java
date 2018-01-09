package twijava.http;

import twijava.http.core.HttpRequest;

public interface HttpRequestInterface {
    // Http Request Method
    String getMethod();
    // get instance http request
    HttpRequest http();
}
