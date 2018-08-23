package manage.request;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.LogUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map.Entry;

public class Request {

    private final IRequest request;
    private static HttpClient httpclient = null;

    static {
        httpclient = HttpClientBuilder.create().build();
    }
	
    public Request(IRequest request) {
        this.request = request;
    }

    public synchronized ApiResponse send() {

         Integer responseCode = null;
         HttpResponse httpResponse = null;
         StringEntity requestEntity = null;

            if (request == null) throw new RuntimeException("Api manage.request shouldn't be null!");
            if (request.getMethod() == null) throw new RuntimeException("Method shouldn't be null");
            if (request.getRequestUrl() == null || request.getRequestUrl().equals(""))
                throw new RuntimeException("Request URL is null or empty!");

            String myResponse = "";
            String body = null;

            if (request.getRequestUrl() == null) throw new RuntimeException("Request URL is null!");
            String requestUrl = request.getRequestUrl();
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("REQUEST: <b><span style=\"color:black;background: yellow;\"> " +request.getMethod().getMethod() + "</span>: " + requestUrl+"</b>");

                // display headers
                if(request.getHeaders() != null)
                for (Entry entry: request.getHeaders().entrySet()) {
                    sb.append("\n" + entry.getKey() + " : " + entry.getValue());
                }

                if (request.getPayload() != null) {
                    //provide the payload
                    body = request.getPayload();
                    sb.append("\n" + body);
                    char arr[] = body.toCharArray();
                    for (char c : arr) {
                        //System.out.print(c);
                    }
                    requestEntity = new StringEntity(body);
                    requestEntity.setContentEncoding("utf-8");
                    requestEntity.setContentType("application/json");
                }
                // If POST method provide entity
                if (request.getMethod() instanceof HttpPost && ((HttpPost) request.getMethod()).getEntity() == null) {
                    ((HttpPost) request.getMethod()).setEntity(requestEntity);
                }

                if (request.getMethod() instanceof HttpPut && ((HttpPut) request.getMethod()).getEntity() == null) {
                    ((HttpPut) request.getMethod()).setEntity(requestEntity);
                }

                if (request.getHeaders() != null) {
                    for (Entry<String, String> entry : request.getHeaders().entrySet()) {
                        request.getMethod().setHeader(entry.getKey(), entry.getValue());
                    }
                }

                LogUtils.info(sb.toString());

                // execute method
                request.getMethod().setURI(new URI(request.getRequestUrl()));
                httpResponse = httpclient.execute(request.getMethod());
                responseCode = httpResponse.getStatusLine().getStatusCode();

                LogUtils.info("STATUS LINE: " + "<b>" + httpResponse.getStatusLine() + "</b>");

                if (httpResponse.getEntity() != null && httpResponse.getEntity().getContent() != null) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader((httpResponse.getEntity().getContent()), "UTF-8"));

                    String output;
                    while ((output = br.readLine()) != null) {
                        myResponse = (myResponse + output);
                    }
                    LogUtils.info("RESPONSE: ========================================================== \n" + StringEscapeUtils.escapeHtml4(myResponse) );
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (request.getMethod() != null) {
                    request.getMethod().releaseConnection();
                }
            }

            return new ApiResponse(httpResponse, myResponse);

    }
}
