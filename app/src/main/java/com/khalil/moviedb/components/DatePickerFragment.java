package com.khalil.moviedb.components;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created on 5/17/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int selectedYear = 0;
    private int selectedMonth = 0;
    private int selectedDay = 0;

    private DateSelectionListener dateSelectionListener;

    public static DatePickerFragment newInstance(DateSelectionListener dateSelectionListener) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.dateSelectionListener = dateSelectionListener;
        return datePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if((selectedYear <= 0) || (selectedMonth <= 0) || (selectedDay <= 0)) {
            Calendar cal = Calendar.getInstance();
            selectedYear= cal.get(Calendar.YEAR);
            selectedMonth = cal.get(Calendar.MONTH);
            selectedDay = cal.get(Calendar.DAY_OF_MONTH);
        }

        return new DatePickerDialog(getActivity(), this, selectedYear, selectedMonth, selectedDay);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        selectedYear = year;
        selectedMonth = month;
        selectedDay = day;

        if(dateSelectionListener == null) {
            return;
        }

        dateSelectionListener.onDateSelected(String.format(Locale.getDefault(),"%02d-%02d-%02d", year, month,day));
    }
}
