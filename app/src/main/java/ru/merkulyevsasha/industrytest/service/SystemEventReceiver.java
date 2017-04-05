package ru.merkulyevsasha.industrytest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sasha_merkulev on 05.04.2017.
 */

public class SystemEventReceiver extends BroadcastReceiver {
    private static String TAG = SystemEventReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_BOOT_COMPLETED)
                || action.equals("android.intent.action.QUICKBOOT_POWERON")
                || action.equals("com.htc.intent.action.QUICKBOOT_POWERON") ) {

            Log.d(TAG, "onReceive: register job service after reboot");
            JobServiceUpdater.registerJobService(context);
        }

    }
}
