package manage.request;

import org.testng.Assert;
import utils.ResponseUtils;

import static org.testng.Assert.assertEquals;

public class RestValidatorBase implements IRestValidator {

    protected ApiRequest apiRequest = null;

    public RestValidatorBase(ApiRequest request) {
        this.apiRequest = request;
    }

    @Override
    public void statusCodeEquals(int statusCode) {
       notNullOrEmpty(apiRequest._response.get().getResponseBody());

        String message = null;
        if(apiRequest._response.get().getResponseBody() != null && !apiRequest._response.get().getResponseBody().equals("")) {
            message = apiRequest._response.get().getResponseBody();
            assertEquals(apiRequest._response.get().getHttpResponse().getStatusLine().getStatusCode(), statusCode, message);

        } else if(apiRequest._response.get().getHttpResponse().getStatusLine() != null) {
            message = apiRequest._response.get().getHttpResponse().getStatusLine() + "";
            assertEquals(apiRequest._response.get().getHttpResponse().getStatusLine().getStatusCode(), statusCode, message);
        }

    }

    @Override
    public void statusCodeEquals(int statusCode, String customErrorMessage) {
        Assert.assertTrue(apiRequest._response.get().getResponseBody() != null );
        Assert.assertEquals(apiRequest._response.get().getHttpResponse().getStatusLine().getStatusCode(), statusCode, customErrorMessage);
    }

    public void responseMessageAttribute(String message) {
        Assert.assertTrue(apiRequest._response.get().getResponseBody() != null);
        String summary = ResponseUtils.getPropertyValue(apiRequest._response.get(), "message");
        Assert.assertEquals(summary, message, "\nExpected: " + message + "\nActual: " + summary);
    }

    public void notNullOrEmpty(String value) {
        Assert.assertNotNull(value);
        Assert.assertTrue(!value.equals(""));
    }

}
