package com.example.ajinkyabadve.mockwebserver;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import io.appflate.restmock.RESTMockServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.appflate.restmock.utils.RequestMatchers.pathEndsWith;

/**
 * Created by ajinkyabadve on 23/11/17.
 */

public class MainActivityRestMockTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);

    @Rule
    public OkHttpIdlingResourceRule okHttpIdlingResourceRule = new OkHttpIdlingResourceRule();

    @Before
    public void setUp() throws Exception {
        RESTMockServer.reset();


    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void followers() throws IOException, InterruptedException {
        RESTMockServer.whenGET(pathEndsWith("octocat"))
                .thenReturnFile("users/octocat.json");

        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("octocat: 1500")));


    }

    @Test
    public void status404() throws IOException, InterruptedException {
        RESTMockServer.whenGET(pathEndsWith("octocat"))
                .thenReturnEmpty(404);

        activityActivityTestRule.launchActivity(null);

        onView(withId(R.id.followers_1))
                .check(matches(withText("404")));


    }
}
