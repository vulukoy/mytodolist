package com.example.vusoft.mytodolist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by vulukoylu on 1/7/2016.
 */
public class MainFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    TaskAdapter taskAdapter;
    TaskManager taskManager;
    List<Task> tasks;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_main, container, false);

        listView = (ListView)view.findViewById(R.id.listViewMain);
        setListViewClick();
        taskManager = new TaskManager(getActivity());
        tasks = taskManager.getTasks();
        taskAdapter = new TaskAdapter(getActivity(), R.layout.task_list, tasks);
        listView.setAdapter(taskAdapter);

        return view;
    }

    public void setListViewClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                showSelectedTask(tasks.get(position));
            }
        });
    }

    public void showSelectedTask(Task task){
        Intent intent = new Intent(getActivity(), NewTaskActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

}
