package manage.request;

import org.apache.http.client.methods.*;

public enum Method {

    GET(new HttpGet()),
    POST(new HttpPost()),
    PUT(new HttpPut()),
    DELETE(new HttpDelete());

    Method(HttpRequestBase method) {
        this.method = method;
    }
    final HttpRequestBase method;

    public HttpRequestBase get() { return method;}

}
