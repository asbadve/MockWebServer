package com.example.ajinkyabadve.mockwebserver;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ajinkyabadve on 21/11/17.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);
    @Rule
    public OkHttpIdlingResourceRule okHttpIdlingResourceRule = new OkHttpIdlingResourceRule();

    @Test
    public void followers() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();

        TestDemoAppClass app = (TestDemoAppClass) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.setBaseUrl(mockWebServer.url("/").toString());

        mockWebServer.enqueue(new MockResponse().setBody(
                "{\"login\" :\"octocat\",\"followers\" :\"2011\" "
        ));
        rule.launchActivity(null);

        onView(withId(R.id.followers))
                .check(matches(withText("octocat: 2011")));

        mockWebServer.shutdown();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

}