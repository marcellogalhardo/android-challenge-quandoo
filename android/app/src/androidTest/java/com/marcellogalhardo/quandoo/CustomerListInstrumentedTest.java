package com.marcellogalhardo.quandoo;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.marcellogalhardo.quandoo.customerlist.CustomerListActivity;
import com.marcellogalhardo.quandoo.utils.HawkUtils;
import com.marcellogalhardo.quandoo.utils.MockWebServerUtils;
import com.marcellogalhardo.quandoo.utils.ThreadUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CustomerListInstrumentedTest {

    private static final int MOCK_WEB_SERVER_PORT = 1234;

    @Rule
    public ActivityTestRule<CustomerListActivity> activityTestRule =
            new ActivityTestRule<>(CustomerListActivity.class, false, true);

    private MockWebServer server = new MockWebServer();

    @Before
    public void setup() throws IOException {
        server.start(MOCK_WEB_SERVER_PORT);
        HawkUtils.clear();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void shouldShowError() throws IOException {
        server.enqueue(MockWebServerUtils.getGenericError());

        launchActivity();
        ThreadUtils.waitMuch();

        onView(withId(R.id.error_layout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowRetryWithSuccessWhenErrorHappens() throws IOException {
        CustomerListActivity activity = activityTestRule.getActivity();
        server.enqueue(MockWebServerUtils.getGenericError());
        server.enqueue(MockWebServerUtils.getFake(activity, R.raw.customer_list));

        launchActivity();
        ThreadUtils.waitMuch();

        onView(withId(R.id.error_layout))
                .check(matches(isDisplayed()));

        ThreadUtils.waitLittle();

        onView(withId(R.id.retry_button))
                .perform(click());

        ThreadUtils.waitTooMuch();

        onView(withId(R.id.recycler_view_customers))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowSuccess() throws IOException {
        CustomerListActivity activity = activityTestRule.getActivity();
        server.enqueue(MockWebServerUtils.getFake(activity, R.raw.customer_list));

        launchActivity();
        ThreadUtils.waitMuch();

        onView(withId(R.id.recycler_view_customers))
                .check(matches(isDisplayed()));
    }

    private void launchActivity() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
    }

}
