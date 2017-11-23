package com.example.ajinkyabadve.mockwebserver;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ajinkyabadve on 23/11/17.
 */

public class MainActivityDispatcherTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);
    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Rule
    public OkHttpIdlingResourceRule okHttpIdlingResourceRule = new OkHttpIdlingResourceRule();

    @Before
    public void setUp() throws Exception {
        TestDemoAppClass app = (TestDemoAppClass) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.setBaseUrl(mockWebServerRule.server.url("/").toString());


    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void followers() throws IOException, InterruptedException {


        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                String path = request.getPath();
                String[] parts = path.split("/");
                String userName = parts[parts.length - 1];
                return new MockResponse().setBody("{ \"login\" : \"" + userName + "\", \"followers\" : " + userName.length() + " }");
            }
        };
        mockWebServerRule.server.setDispatcher(dispatcher);

        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("octocat: 7")));
        onView(withId(R.id.followers_2))
                .check(matches(withText("swankjesse: 10")));
        onView(withId(R.id.followers_3))
                .check(matches(withText("chiuki: 6")));


    }
}
