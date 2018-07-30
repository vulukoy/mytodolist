package com.example.vusoft.mytodolist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TaskDAO extends BaseDAO {

    public TaskDAO(Context context) {
        super(context);
    }

    public long delete(int taskID) {
        return database.delete(ModelManager.TASK_TABLE, "id=?", new String[]{taskID + ""});
    }

    public long update(Task task) {
        ContentValues values = new ContentValues();
        Map<String, String> columns = Task.getColumns();
        values.put(columns.get("id"), task.getId());
        values.put(columns.get("subject"), task.getSubject());
        values.put(columns.get("description"), task.getDescription());
        values.put(columns.get("timestamp"), task.getTimestamp());
        values.put(columns.get("modified"), TaskUtil.getCurrentTime());

        return save(values);
    }

    public long insert(Task task) {
        ContentValues values = new ContentValues();
        Map<String, String> columns = Task.getColumns();
        values.put(columns.get("subject"), task.getSubject());
        values.put(columns.get("description"), task.getDescription());
        values.put(columns.get("timestamp"), task.getTimestamp());
        values.put(columns.get("created"), TaskUtil.getCurrentTime());
        values.put(columns.get("modified"), TaskUtil.getCurrentTime());

        return save(values);
    }

    public long save(ContentValues values) {
        if (values.get("id") == null || values.get("id").toString() == "0") return database.insert(ModelManager.TASK_TABLE, null, values);

        return database.update(ModelManager.TASK_TABLE, values, "id=?", new String[]{String.valueOf(values.get("id"))});
    }

    public Factory getFactory(){
        return ModelManager.getFactory();
    }

    public List<Task> getTasksByCursor(Cursor cursor){
        Map<String, String> columns = Task.getColumns();

        List<Task> tasks = new ArrayList<Task>();
        while(cursor.moveToNext()) {
            Task task = getFactory().createTask(null);
            task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columns.get("id"))));
            task.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(columns.get("subject"))));
            task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(columns.get("description"))));
            task.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(columns.get("timestamp"))));
            task.setCreated(cursor.getLong(cursor.getColumnIndexOrThrow(columns.get("created"))));
            task.setModified(cursor.getLong(cursor.getColumnIndexOrThrow(columns.get("modified"))));

            tasks.add(task);
        }

        return tasks;
    }

    public String [] getColumnNames(){
        Map<String, String> columns = Task.getColumns();

        return new String[]{
                columns.get("id"),
                columns.get("subject"),
                columns.get("description"),
                columns.get("timestamp"),
                columns.get("created"),
                columns.get("modified")
        };
    }

    public List<Task> getPastTasks(){
        return getTasksByCursor(database.query(
                Task.getTableName(),
                this.getColumnNames(),null,null,null,null,null));
    }

    public List<Task> getTasks(){
        return getTasksByCursor(database.query(
                Task.getTableName(),
                this.getColumnNames(),null,null,null,null,null));
    }

}