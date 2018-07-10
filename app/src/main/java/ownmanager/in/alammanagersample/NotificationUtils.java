package ownmanager.in.alammanagersample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

public class NotificationUtils {
    public static final String ALARM_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    public static final int ALARM_REMINDER_NOTIFICATION_ID = 1234;
    private static final int ALARM_REMINDER_PENDING_INTENT_ID = 3417;

    //This pending intent is to relaunch the application when click the notification
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context,
                ALARM_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                //FLAG_UPDATE_CURRENT -> keeps this instance valid and just updates the extra data associated with it coming from new intent
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
    public static void createNotifications(Context context) {
        /**Get reference to notification manager*/
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        /**From Oreo we need to display notifications in the notification channel*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    ALARM_REMINDER_NOTIFICATION_CHANNEL_ID, //String ID
                    "Alarm Manager", //Name for the channel
                    NotificationManager.IMPORTANCE_HIGH); //Importance for the notification , In high we get headsUp notification
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, ALARM_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.android)
                .setContentTitle("Alarm Manager") // Title
                .setContentText("THis is an alarm manager service") //Text
                .setContentIntent(contentIntent(context)) //pending Intent (check its fn)

                .setAutoCancel(true); //Notification will go away when user clicks on it
        /**this will give heads up notification on versions below Oreo*/
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        //AlARM_REMINDER_NOTIFICATION_ID -> this ID can be used if the notification have to ba cancelled
        notificationManager.notify(ALARM_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }
}
