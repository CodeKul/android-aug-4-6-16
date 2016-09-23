package com.melayer.notifications;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = null;

        if(getTag().equals(DialogActivity.TAG_ALERT))dialog = alert();
        else if(getTag().equals(DialogActivity.TAG_DATE)) dialog = datePicker();
        else if(getTag().equals(DialogActivity.TAG_TIME)) dialog = timePicker();
        else if(getTag().equals(DialogActivity.TAG_PROGRESS)) dialog = progress();
        else dialog = custom();

        return dialog;
    }

    private Dialog custom() {

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog,null,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(dialogView);

        return builder.create();
    }

    private Dialog progress() {
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setTitle(getResources().getString(R.string.progressTitle));
        progress.setMessage(getResources().getString(R.string.progressMessage));

        return progress;
    }

    private Dialog timePicker() {
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                StringBuilder builder = new StringBuilder();
                builder.append("")
                        .append(hourOfDay)
                        .append(" : ")
                        .append(minute);
                mt(builder.toString());

                String str = ""+hourOfDay +" :" +minute; //costly
            }
        }, 12, 46, false);
        return timePicker;
    }

    private Dialog alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getResources().getString(R.string.alertTitle))
                .setMessage(getResources().getString(R.string.alertMessage))
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(getResources().getString(R.string.alertPositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mt("Ok");
                    }
                })
                .setNegativeButton(getResources().getString(R.string.alertNegative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mt("Cancel");
                    }
                });
        return builder.create();
    }

    private Dialog datePicker(){
        DatePickerDialog datePicker =
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("")
                                .append(dayOfMonth)
                                .append(" - ")
                                .append(month)
                                .append(" - ")
                                .append(year);

                        mt(builder.toString());
                    }
                }, 2016, 9, 23);
        return datePicker;
    }

    private void mt(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
