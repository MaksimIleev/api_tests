package manage.request;

import org.apache.http.client.methods.HttpRequestBase;
import utils.LogUtils;
import utils.ResponseUtils;

import java.util.Map;

public abstract class ApiRequest extends ThreadLocal<ApiRequest> implements IRequest {

    protected ThreadLocal<String> _uri = new ThreadLocal<>();
    protected ThreadLocal<String> _domain = new ThreadLocal<>();
    protected ThreadLocal<String> _payload = new ThreadLocal<>();
    protected ThreadLocal<HttpRequestBase> _method = new ThreadLocal<>();
    protected ThreadLocal<Map<String, String>> _headers = new ThreadLocal<>();
    protected ThreadLocal<String> _requestUrl = new ThreadLocal<>();
    protected ThreadLocal<ApiResponse> _response = new ThreadLocal<>();
    protected ThreadLocal<Request> _request = new ThreadLocal<>();

    protected ApiRequest() {};
    protected abstract String requestInfo();

    protected ApiRequest(String domain, String uri, String payload, HttpRequestBase method, Map<String, String> headers, String... requestUrl) {
        set(this);
        this._uri.set(uri);
        this._payload.set(payload);
        this._method.set(method);
        this._headers.set(headers);

       if(uri != null) {
            this._domain.set(domain);
        }
        this._requestUrl.set(this._domain.get() + this._uri.get().replaceAll("\\s", "%20"));

    }

    @Override
    public synchronized ApiResponse sendRequest() {
        _request.set( new Request(get()));
        LogUtils.info("\n\n" + requestInfo());
        _response.set(_request.get().send());

        return _response.get();
    }

    public synchronized ApiResponse sendRequest(Boolean isSaveLog) {
        synchronized (this) {
            _request.set( new Request(get()));
            if(isSaveLog)
            LogUtils.info("\n\n" + requestInfo());
            _response.set(_request.get().send());
        }
        return _response.get();
    }

    @Override
	public String getRequestUrl() {
		return _requestUrl.get();
	}

	@Override
	public String getPayload() {
		return _payload.get();
	}

    @Override
    public String getUri() {
        return _uri.get();
    }

    @Override
    public String getDomain() {
        return _domain.get();
    }

    @Override
    public HttpRequestBase getMethod() {
        return _method.get();
    }

    @Override
    public Map<String, String> getHeaders() {
        return _headers.get();
    }

    public ApiResponse getResponse() {
        return _response.get();
    }

    public synchronized <T> T parse(Class<T> t) {
       return (T) ResponseUtils.parse(t, _response.get().getResponseBody());
    }

}
