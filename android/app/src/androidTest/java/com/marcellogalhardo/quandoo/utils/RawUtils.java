package com.marcellogalhardo.quandoo.utils;

import android.content.res.Resources;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.InputStream;

public class RawUtils {

    private static final String TAG = RawUtils.class.getSimpleName();

    public String readTextFile(Resources resources, @RawRes int rawId) {
        String result = "";
        try {
            InputStream inputStream = resources.openRawResource(rawId);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            result = new String(b);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

}
