package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import manage.request.ApiResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ResponseUtils {
	
	public static synchronized String getPropertyValue(ApiResponse response, String parameter) {
        if (parameter == null || response == null)
            throw new RuntimeException("propertyName and/or responseBody is null");

        if (response.getResponseBody() != null) {
            JsonObject json = new Gson().fromJson(response.getResponseBody(), JsonObject.class);
            String value = json.get(parameter).toString();
            if(value.startsWith("\"") && value.endsWith("\""))  {
                value = value.substring(1, value.length() - 1);
            }
            return value;
        } else
            return null;
    }

    public static synchronized String getPropertyValue(String response, String parameter) {
        if (parameter == null || response == null)
            throw new RuntimeException("propertyName and/or responseBody is null");

        if (response != null) {
            JsonObject json = new Gson().fromJson(response, JsonObject.class);
            String value = json.get(parameter).toString();
            if(value.startsWith("\"") && value.endsWith("\""))  {
                value = value.substring(1, value.length() - 1);
            }
            return value;
        } else
            return null;
    }

    public synchronized static <T> T parse(Class<T> clazz, String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Object obj = gson.fromJson(json, clazz);
        return (T) obj;
    }

    public synchronized static <T, E> T parse(Class<T> clazz, String json, Class<E> adapterClass, JsonDeserializer<E> adapter) {
        GsonBuilder builder = new GsonBuilder();
        if(adapter != null)
            builder.registerTypeAdapter(adapterClass, adapter);
        Gson gson = builder.create();
        Object obj = gson.fromJson(json, clazz);
        return (T) obj;
    }

	public static int getStatusCode(ApiResponse response) {
		if(response.getHttpResponse().getStatusLine() == null)
			throw new RuntimeException("Response doesn't have response code");
		return response.getHttpResponse().getStatusLine().getStatusCode(); 
	}

    public static String getString(InputStream is) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        String output = null;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        try {
            output = result.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return output;
    }

}
