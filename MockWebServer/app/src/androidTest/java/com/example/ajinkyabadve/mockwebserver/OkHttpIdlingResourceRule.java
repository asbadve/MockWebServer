package com.example.ajinkyabadve.mockwebserver;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import okhttp3.internal.tls.SslClient;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class OkHttpIdlingResourceRule implements TestRule {
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                SslClient localhost = SslClient.localhost();
                IdlingResource idlingResource = OkHttp3IdlingResource.create(
                        "okhttp", OkHttp.getInstance(localhost.socketFactory, localhost.trustManager));

                Espresso.registerIdlingResources(idlingResource);

                base.evaluate();

                Espresso.unregisterIdlingResources(idlingResource);
            }
        };
    }
}
