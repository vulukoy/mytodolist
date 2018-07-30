package com.example.vusoft.mytodolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {
    TaskManager taskManager;
    EditText editTextSubject;
    EditText editTextDescription;
    EditText editTextDate;
    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;
    Task task;
    Toolbar myChildToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        preCreate();
        setViewItems();
        checkTask();
    }

    public void preCreate(){
        task = (Task) getIntent().getParcelableExtra("task");
    }

    public boolean isEdit(){
        return (task != null) ? true : false;
    }

    public void checkTask(){
        if (isEdit()){
            editTextSubject.setText(task.getSubject());
            editTextDescription.setText(task.getDescription());
            editTextDate.setText(TaskUtil.getDateDefault(task.getTimestamp()));
            setTitle(R.string.title_activity_edit_task);
        }
    }

    public void setViewItems(){
        editTextSubject = (EditText) this.findViewById(R.id.etxt_subject);
        editTextDescription = (EditText) this.findViewById(R.id.etxt_description);
        editTextDate = (EditText) this.findViewById(R.id.etxt_date);

        taskManager = new TaskManager(NewTaskActivity.this);
        setDatePickerDialog();

        editTextDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        myChildToolbar =
                (Toolbar) findViewById(R.id.my_child_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void setDatePickerDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(NewTaskActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        editTextDate.setText(TaskUtil.formatter.format(dateCalendar
                                .getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_task_confirm)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        taskManager.delete(task);
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        showMainActivity();                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog confirm = builder.create();
        confirm.setTitle(R.string.delete_task_confirm_header);
        confirm.show();
    }

    public void addNewTask(){
        String subject = editTextSubject.getText().toString();
        String desc = editTextDescription.getText().toString();
        long date = TaskUtil.strToTime(editTextDate.getText().toString());

        if (isEdit())
            taskManager.update(task, subject, desc, date);
        else
            taskManager.insert(subject, desc, date);
    }

    public void showMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        menu.findItem(R.id.action_delete_task).setVisible(isEdit());

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
        else if (id == R.id.action_save_task){
            addNewTask();
            showMainActivity();
        }
        else if (id == R.id.action_delete_task){
            confirmDelete();
        }

        return super.onOptionsItemSelected(item);
    }

/*
    public void setEvents(){
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewTask();
                showMainActivity();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });

    }
*/

}
