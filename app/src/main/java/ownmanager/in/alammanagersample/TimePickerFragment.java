package ownmanager.in.alammanagersample;
/**
 * Fragment for time picker */
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

public class TimePickerFragment extends android.support.v4.app.DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //getting current time from system
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hour,min, DateFormat.is24HourFormat(getActivity()));
        //DateFormat.is24HourFormat(getActivity()) -> check whether the phone is set to Am or Pm
    }
}
