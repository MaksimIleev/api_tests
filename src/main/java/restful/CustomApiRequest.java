package restful;

import org.apache.http.client.methods.HttpRequestBase;
import manage.request.ApiRequest;
import manage.request.IRestValidator;
import manage.request.Method;
import manage.request.RestValidatorBase;
import utils.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class CustomApiRequest extends ApiRequest {

    private final String uri;
    private final String payload;
    private final HttpRequestBase method;
    private final Map<String, String> headers;
    private final String domain;
    private final RestValidatorBase validator;

    private CustomApiRequest(final Builder builder) {
        super(builder.domain, builder.uri, builder.payload, builder.method, builder.headers, builder.requestUrl);
        this.domain = builder.domain;
        this.uri = builder.uri;
        this.payload = builder.payload;
        this.method = builder.method;
        this.headers = builder.headers;
        this.validator = new RestValidatorBase(this);
    }

    @Override
    protected String requestInfo() {
        return "<<<<<<<<<< CUSTOM API >>>>>>>>>>";
    }

    public static Builder Builder() {return new Builder();}

    public static class Builder {

        private String uri = "";
        private String payload = null;
        private HttpRequestBase method = null;
        private Map<String, String> headers = new HashMap<>();
        private String domain = null;
        private String requestUrl = null;

        public Builder setURI(String uri) {
            this.uri += uri;
            return this;
        }

        public Builder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder method(Method method) {
            this.method = method.get();
            return this;
        }

        public Builder setHeader(String headerKey, String headerValue) {
            this.headers.put(headerKey, headerValue);
            return this;
        }

        public Builder setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public CustomApiRequest build() {
            return new CustomApiRequest(this);
        }
    }

    public IRestValidator Validate() {
        return validator;
    }

    public <T> Object parseResponse(Class<T> t) {
        try {
            return (T) ResponseUtils.parse(Class.forName(t.getName()), getResponse().getResponseBody());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}