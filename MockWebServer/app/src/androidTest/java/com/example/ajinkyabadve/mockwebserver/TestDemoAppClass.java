package com.example.ajinkyabadve.mockwebserver;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.internal.tls.SslClient;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class TestDemoAppClass extends AppClass {
    private String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    @Override
    public SSLSocketFactory getSSLSocketFactory() {
        return SslClient.localhost().socketFactory;
    }

    @Override
    public X509TrustManager getX509TrustManager() {
        return SslClient.localhost().trustManager;
    }
}
