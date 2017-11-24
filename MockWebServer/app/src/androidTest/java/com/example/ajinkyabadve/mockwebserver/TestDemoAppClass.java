package com.example.ajinkyabadve.mockwebserver;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class TestDemoAppClass extends AppClass {
    String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }


    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
