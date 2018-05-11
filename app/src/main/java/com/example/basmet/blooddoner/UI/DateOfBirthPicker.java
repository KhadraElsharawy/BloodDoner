package com.example.basmet.blooddoner.UI;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import com.example.basmet.blooddoner.UI.SignUpActivity;

import java.util.Calendar;

/**
 * Created by basmet on 4/28/2018.
 */

public class DateOfBirthPicker extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
    DatePickerDialog datePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
        SignUpActivity.edDateOfBirth.setText(new StringBuilder().append(dateOfMonth).append("/").append(month+1).append("/").append(year));
    }

}
