package com.example.ajinkyabadve.mockwebserver;

import android.app.Application;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public abstract class AppClass extends Application {
    public String getBaseUrl() {
        return "https://api.github.com";
    }
}
