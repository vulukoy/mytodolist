package com.example.vusoft.mytodolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vulukoylu on 12/20/2015.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    public Context context;
    public int resource;
    public List<Task> tasks;

    public TaskAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);

        this.context = context;
        this.resource = resource;
        this.tasks = tasks;
    }

    private class ViewHolder {
        TextView taskIdTxt;
        TextView taskSubject;
        TextView taskDescription;
        TextView taskTimestamp;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_list, null);
            holder = new ViewHolder();

            holder.taskIdTxt = (TextView) convertView
                    .findViewById(R.id.txt_task_id);
            holder.taskSubject = (TextView) convertView
                    .findViewById(R.id.txt_task_subject);
            holder.taskDescription = (TextView) convertView
                    .findViewById(R.id.txt_task_description);
            holder.taskTimestamp = (TextView) convertView
                    .findViewById(R.id.txt_task_timestamp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Task task = (Task) getItem(position);
        holder.taskIdTxt.setText(task.getId() + "");
        holder.taskSubject.setText(task.getSubject());
        holder.taskDescription.setText(task.getDescription());
        holder.taskTimestamp.setText(TaskUtil.getDateDefault(task.getTimestamp()));

        return convertView;
    }

    @Override
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return this.tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public void add(Task task) {
        this.tasks.add(task);
        notifyDataSetChanged();
        super.add(task);
    }

    @Override
    public void remove(Task task) {
        this.tasks.remove(task);
        notifyDataSetChanged();
        super.remove(task);
    }

}
