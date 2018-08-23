package manage.request;

import org.apache.http.HttpResponse;

public class ApiResponse {

	private HttpResponse httpResponse;
	private String responseBody;

	public ApiResponse(HttpResponse response, String responseBody) {
		this.httpResponse = response;
        //this.body = body;
        this.responseBody = responseBody;
	}
	
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

    public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

    public String getResponseBody() {
		return responseBody;
	}
}
