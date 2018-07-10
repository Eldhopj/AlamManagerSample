package ownmanager.in.alammanagersample;
/**Aim : To create an Alarm manager with notification and time picker and cancel the alarm
 * check the Manifest
 *Tags : timePicker, Notification, Broadcast receiver*/
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private TextView alramTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alramTV = findViewById(R.id.textView);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0); // seconds to be zero when alarm starts

        updateTimeNext(calendar);
        startAlarm(calendar);
    }

    /**Method for alarm manager*/
    private void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,intent,0); //requestCode must be different for intents

        if (calendar.before(Calendar.getInstance())){ // Prevents alarm if took a past value
            calendar.add(Calendar.DATE,1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            //setExact -> Fire alarm at exact point
            //AlarmManager.RTC_WAKEUP -> Wake the system if its goes off
        }
    }

    //Method for setting alarm time into textView
    private void updateTimeNext(Calendar calendar) {
        String timeText = "Alarm Set For: " + DateFormat.getTimeInstance().format(calendar.getTime());
        alramTV.setText(timeText);
    }

    public void timePicker(View view) { // calling timepickerFragment
        DialogFragment timepicker = new TimePickerFragment();
        timepicker.show(getSupportFragmentManager(),"time picker");
    }

    public void cancelAlarm(View view) {
        cancelAlarm();
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,intent,0); //requestCode must be different for intents

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            //setExact -> Fire alarm at exact point
            //AlarmManager.RTC_WAKEUP -> Wake the system if its goes off
        }
    }
}
