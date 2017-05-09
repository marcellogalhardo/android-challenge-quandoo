package com.marcellogalhardo.quandoo.base;

import android.support.v7.app.AppCompatActivity;

import com.marcellogalhardo.quandoo.MainApplication;
import com.marcellogalhardo.quandoo.MainComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }

    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

}
