package com.example.vusoft.mytodolist;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.example.vusoft.mytodolist.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    TaskAdapter taskAdapter;
    TaskManager taskManager;
    List<Task> tasks;
    Toolbar myToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        listView = (ListView)this.findViewById(R.id.listViewMain);
        setListViewClick();
        taskManager = new TaskManager(MainActivity.this);
        tasks = taskManager.getTasks();
        taskAdapter = new TaskAdapter(this, R.layout.task_list, tasks);
        listView.setAdapter(taskAdapter);
*/

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*starting tabs*/
        addTabs();
    }

    public void setListViewClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                showSelectedTask(tasks.get(position));
            }
        });
    }

    public void showSelectedTask(Task task){
        Intent intent = new Intent(this, NewTaskActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    public void showNewTaskWindow(){
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    public void addTabs(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_main_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_history_title));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Toast.makeText(getApplicationContext(), cities[id],Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add_task){
            //Log.d("MainActivity", "Add a new task");
            //this.showTaskWindow();
            showNewTaskWindow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
