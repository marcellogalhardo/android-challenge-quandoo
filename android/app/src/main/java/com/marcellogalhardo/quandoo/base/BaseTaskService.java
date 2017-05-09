package com.marcellogalhardo.quandoo.base;

import com.google.android.gms.gcm.GcmTaskService;
import com.marcellogalhardo.quandoo.MainApplication;
import com.marcellogalhardo.quandoo.MainComponent;

public abstract class BaseTaskService extends GcmTaskService {

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }

    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

}
