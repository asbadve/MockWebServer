package com.example.ajinkyabadve.mockwebserver;

import io.appflate.restmock.RESTMockServer;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class TestDemoAppClass extends AppClass {
    String baseUrl;

    @Override
    public String getBaseUrl() {
        return RESTMockServer.getUrl();
    }


    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
