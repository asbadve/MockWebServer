package com.example.ajinkyabadve.mockwebserver;

import android.app.Application;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class AppClass extends Application {
    public String getBaseUrl() {
        return "https://api.github.com";
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return null;  // Use system default
    }

    public X509TrustManager getX509TrustManager() {
        return null;  // Use system default
    }
}
