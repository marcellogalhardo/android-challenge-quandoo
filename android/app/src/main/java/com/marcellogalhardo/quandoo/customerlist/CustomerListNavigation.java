package com.marcellogalhardo.quandoo.customerlist;

import android.content.Context;
import android.content.Intent;

public class CustomerListNavigation {

    public static void start(Context context) {
        Intent intent = new Intent(context, CustomerListActivity.class);
        context.startActivity(intent);
    }

}
