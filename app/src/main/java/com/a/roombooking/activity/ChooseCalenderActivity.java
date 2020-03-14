package com.a.roombooking.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.R;

import java.util.Calendar;

public class ChooseCalenderActivity extends AppCompatActivity {
    TextView tv_dob;
    String date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_calender);
        tv_dob=(TextView)findViewById(R.id.tv_dob);
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timedatepicker();
            }
        });
    }

    public void timedatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(ChooseCalenderActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                //date = month + "/" + day + "/" + year;
                date=year + "/" + month + "/" + day;
                tv_dob.setText(date);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                ChooseCalenderActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
