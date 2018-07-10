package ownmanager.in.alammanagersample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertBroadcastReceiver extends BroadcastReceiver {
    @Override
    // This method is called when the alarm is fired
    public void onReceive(Context context, Intent intent) {
        NotificationUtils.createNotifications(context);
    }
}
