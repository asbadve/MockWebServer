package com.example.ajinkyabadve.mockwebserver;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public abstract class OkHttp {
    private static OkHttpClient instance = null;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            instance = new OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.SECONDS)
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .build();
        }
        return instance;
    }
}
