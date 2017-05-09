package com.marcellogalhardo.quandoo.tablemap;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;
import com.marcellogalhardo.quandoo.base.BaseTaskService;
import com.marcellogalhardo.quandoo.data.store.TableRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class TableMapTaskService extends BaseTaskService {

    private static final String TAG = TableMapTaskService.class.getSimpleName();

    private static int PERIOD = 10;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    TableRepository tableRepository;

    public static PeriodicTask create() {
        return new PeriodicTask.Builder()
                .setPeriod(TimeUnit.MINUTES.toSeconds(PERIOD))
                .setRequiredNetwork(PeriodicTask.NETWORK_STATE_ANY)
                .setTag(TAG)
                .setService(TableMapTaskService.class)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getMainComponent().inject(this);
    }

    @Override
    public int onRunTask(TaskParams params) {
        Disposable disposable = tableRepository.fetchTables().subscribe();
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}