package com.marcellogalhardo.quandoo.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import okhttp3.mockwebserver.MockResponse;

public class MockWebServerUtils {

    @NonNull
    public static MockResponse getFake(@NonNull Activity activity, @RawRes int fileId) {
        RawUtils rawUtils = new RawUtils();
        String body = rawUtils.readTextFile(activity.getResources(), fileId);
        return new MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(body);
    }

    @NonNull
    public static MockResponse getGenericError() {
        return new MockResponse().setResponseCode(500);
    }

}
