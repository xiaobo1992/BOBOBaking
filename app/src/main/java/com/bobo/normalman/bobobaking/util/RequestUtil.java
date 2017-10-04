package com.bobo.normalman.bobobaking.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaobozhang on 9/30/17.
 */

public class RequestUtil {

    private static Response makeGetRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    private static Request buildGetRequest(String url) {
        return new Request.Builder().url(url).build();
    }

    public static String request(String url) throws IOException {
        Request request = buildGetRequest(url);
        Response response = makeGetRequest(request);
        return response.body().string();
    }
}
