package com.kl.alarmclock;

import android.app.TimePickerDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;


public class Main extends AppCompatActivity {

    private TextClock clk;
    private ListView list;
    private ArrayAdapter<String> mAdapter;
    private Toast mToast;
    private int mHour, mMinute;
    private TimePickerDialog chooseTime;
    private SQLiteDatabase  dataBase;
    private List<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*

            TODO:DataBase stuff

         */

        mToast = new Toast(this);
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        alarms = new ArrayList<>();

        chooseTime = new TimePickerDialog(this,
                R.style.AppTheme_Dialog, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        Toast.makeText(view.getContext(), mHour+"h:"+mMinute, Toast.LENGTH_SHORT).show();
                        Log.d("PICKER", "creating alarm!");
                        alarms.add(new Alarm(mHour, mMinute));

                        updateList();
                    }
                }, mHour, mMinute, true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setImageResource(R.drawable.ic_alarm_add_black);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Pressed", Toast.LENGTH_SHORT).show();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                chooseTime.updateTime(mHour, mMinute);
                chooseTime.show();
            }
        });

        clk = (TextClock) findViewById(R.id.textClock);
        clk.setFormat12Hour(null);
        clk.setFormat24Hour("HH:mm:ss");

        list = (ListView) findViewById(R.id.listAlarms);
        ArrayList<String> str = new ArrayList<String>();
        for(int i =0; i < 5; i++){
            str.add("String"+i);
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        list.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateList(){
        mAdapter.clear();
        List<String> str = new ArrayList<>();
        for(int i =0; i < alarms.size(); i++){
            str.add(alarms.get(i).getHours()+"h"+alarms.get(i).getMinutes());
        }
        mAdapter.addAll(str);
        mAdapter.notifyDataSetChanged();
    }
}
