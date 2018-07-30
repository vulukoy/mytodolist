package com.example.vusoft.mytodolist;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vulukoylu on 1/2/2016.
 */
public class BaseDAO {
    protected SQLiteDatabase database;
    private TaskDBHelper dbHelper;
    private Context mContext;

    public BaseDAO(Context context) {
        this.mContext = context;
        dbHelper = TaskDBHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

}
