package com.kl.alarmclock;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

/**
 * Created by Alexandre on 07/11/2017.
 */

public class Alarm {
    private int hours;
    private int minutes;
    private boolean active;
    private String music;
    private boolean vibration;
    private boolean repeat;
    private List<Integer> days = new ArrayList<>();

    Alarm(){
        hours=0;
        minutes=0;
        active=false;
        music="";
        vibration=false;
        repeat=false;
        for(int i = 0; i < 7; i++){
            days.add(0);
        }
    }

    Alarm(int mHour, int mMinute){
        hours=mHour;
        minutes=mMinute;
        active=false;
        music="";
        vibration=false;
        repeat=false;
        for(int i = 0; i < 7; i++){
            days.add(0);
        }
    }

    public void setTime(int mHour, int mMinute){
        hours=mHour;
        minutes=mMinute;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public boolean isVibration() {
        return vibration;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public List<Integer> getDays() {
        return days;
    }

    public boolean toggleRepeatDay(int day){
        if (day < 0 || day >= 7)
                return false;
        if(days.get(day) == 0)
            days.set(day, 1);
        else
            days.set(day, 0);
        return true;
    }
}
