package com.example.ajinkyabadve.mockwebserver;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;


/**
 * Created by ajinkyabadve on 21/11/17.
 */
public class MainActivityTest {
    public static final String SUCCESS_JSON = "{ \"login\" : \"octocat\", \"followers_1\" : 1500 }";
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
    public void followers_1() throws IOException, InterruptedException {


        mockWebServerRule.server.enqueue(new MockResponse().setBody(SUCCESS_JSON));


        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("octocat: 1500")));

        RecordedRequest recordedRequest = mockWebServerRule.server.takeRequest();

        assertEquals("/users/octocat", recordedRequest.getPath());

    }

    @Test
    public void status404() throws Exception {


        mockWebServerRule.server.enqueue(new MockResponse().setResponseCode(404));

        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("404")));

    }

    @Test
    public void malformedJson() throws Exception {


        mockWebServerRule.server.enqueue(new MockResponse().setBody("json"));

        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("JsonEncodingException")));

    }

    @Test
    public void timeOut() throws Exception {


        mockWebServerRule.server.enqueue(
                new MockResponse().setBody(SUCCESS_JSON).throttleBody(1, 1, TimeUnit.SECONDS));
        activityActivityTestRule.launchActivity(null);
        onView(withId(R.id.followers_1))
                .check(matches(withText("SocketTimeoutException")));
    }


}