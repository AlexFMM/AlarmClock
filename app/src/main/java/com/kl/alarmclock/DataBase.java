package com.kl.alarmclock;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by alexf on 14/11/2017.
 */

public class DataBase extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "alarmInfo";
    // Contacts table name
    private static final String TABLE_ALARMS = "alarms";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_MUSIC = "music";
    private static final String KEY_VIB = "vibration";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_DAYS = "days";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALARMS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME + " TEXT,"
        + KEY_ACTIVE + " INTEGER," + KEY_MUSIC + " TEXT," + KEY_VIB
        + " INTEGER," + KEY_REPEAT + " INTEGER," + KEY_DAYS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        // Creating tables again
        onCreate(db);
    }
}
