package com.marcellogalhardo.quandoo.tablemap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.marcellogalhardo.quandoo.data.Customer;

public class TableMapNavigation {

    public static void start(Context context, Customer customer) {
        Intent intent = new Intent(context, TableMapActivity.class);
        intent.putExtra(Customer.TAG, customer);
        context.startActivity(intent);
    }

}
