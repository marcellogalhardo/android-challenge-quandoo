package com.marcellogalhardo.quandoo.utils;

import com.orhanobut.hawk.Hawk;

public class HawkUtils {

    public static void clear() {
        Hawk.deleteAll();
    }

}
