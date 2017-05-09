package com.marcellogalhardo.quandoo.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.marcellogalhardo.quandoo.base.BaseActivity;
import com.marcellogalhardo.quandoo.customerlist.CustomerListNavigation;
import com.marcellogalhardo.quandoo.tablemap.TableMapTaskService;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGcmTasks();
        CustomerListNavigation.start(this);
        finish();
    }

    private void initGcmTasks() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            GcmNetworkManager gcm = GcmNetworkManager.getInstance(this);
            gcm.schedule(TableMapTaskService.create());
        } else {
            Toast.makeText(this, "Please install Google Play Services...", Toast.LENGTH_SHORT).show();
        }

    }
}