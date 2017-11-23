package com.example.ajinkyabadve.mockwebserver;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import io.appflate.restmock.android.RESTMockTestRunner;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class CustomTestRunner extends RESTMockTestRunner {
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestDemoAppClass.class.getName(), context);
    }
}