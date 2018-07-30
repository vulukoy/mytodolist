package com.example.vusoft.mytodolist;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by vulukoylu on 12/19/2015.
 */
public class TaskManager {
    public Context context;
    private TaskDAO taskDAO;

    public TaskManager(Context context){
        this.context = context;
    }

    public TaskDAO getTaskDAO(){
        if (this.taskDAO == null)
            this.taskDAO = new TaskDAO(this.context);

        return this.taskDAO;
    }

    public Factory getFactory(){
        return ModelManager.getFactory();
    }

    public Task prepareTask(Task task, String subject, String description, long timestamp){
        task.setSubject(subject);
        task.setDescription(description);
        task.setTimestamp(timestamp);

        return task;
    }

    public boolean delete(Task task){
        this.getTaskDAO().delete(task.getId());

        return true;
    }

    public Task update(Task task, String subject, String description, long timestamp){
        task = prepareTask(task, subject, description, timestamp);
        this.getTaskDAO().update(task);

        return task;
    }

    public Task insert(String subject, String description, long timestamp){
        Task task = prepareTask(getFactory().createTask(null), subject, description, timestamp);
        task.setId(0);
        this.getTaskDAO().insert(task);

        return task;
    }

    public List<Task> getTasks(){
        return this.getTaskDAO().getTasks();
    }

}
