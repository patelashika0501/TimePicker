package com.example.timepicker;

import static java.util.Calendar.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    TextView setTime;
    private  String am_pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=findViewById(R.id.timePicker);
        setTime=findViewById(R.id.setTime);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hours, int miniutes)
            {
                setTime.setText(""+hours+":"+miniutes);
                timePicker.setVisibility(View.GONE);
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
             Calendar calendar = getInstance();
            @Override
            public void onClick(View view) {

                TimePickerDialog dialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int miniutes) {
                        calendar.set(calendar.HOUR_OF_DAY,hours);
                        calendar.set(calendar.MINUTE,miniutes);

                        if(calendar.get(Calendar.AM_PM)== Calendar.AM) {
                            am_pm="Am";
                        }else if(calendar.get(Calendar.AM_PM)== Calendar.PM){
                            am_pm="PM";
                        }
                        String strHrsShow = (calendar.get(Calendar.HOUR)==0)?"12":Integer.toString(miniutes);

                        setTime.setText(""+strHrsShow+":"+am_pm);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                dialog.show();

            }
        });
    }
}