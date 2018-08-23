package manage.request;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.Map;

public interface IRequest {
	
	 public String getRequestUrl();
	 public String getPayload();
	 public String getUri();
	 public String getDomain();
	 public HttpRequestBase getMethod();
	 public Map<String, String> getHeaders();
	 public ApiResponse sendRequest();

}
