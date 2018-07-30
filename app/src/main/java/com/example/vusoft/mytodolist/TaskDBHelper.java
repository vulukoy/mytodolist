package com.example.vusoft.mytodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Map;

public class TaskDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "com.example.vusoft.mytodolist.db";
    public static final int DB_VERSION = 1;

    private static TaskDBHelper instance;


    public static synchronized TaskDBHelper getHelper(Context context) {
        if (instance == null)
            instance = new TaskDBHelper(context);
        return instance;
    }

    public TaskDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery = getSqlForTaskTable();
        //Log.d("TaskDBHelper","Query to form table: "+sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + Task.getTableName());
        onCreate(sqlDB);
    }

    public String getSqlForTaskTable(){
        Map<String, String> columns = Task.getColumns();

        return String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s INTEGER)",
                Task.getTableName() ,
                columns.get("id"),
                columns.get("subject"),
                columns.get("description"),
                columns.get("timestamp"),
                columns.get("created"),
                columns.get("modified"));
    }
}