package com.example.vusoft.mytodolist;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by vulukoylu on 12/28/2015.
 */
public class ModelManager {
    private static Factory factory;
    public static final String TASK_TABLE = "task";
    public static final String TASK_CLASS = "com.example.vusoft.mytodolist.Task";

    public static Factory getFactory(){
        if (factory == null)
            factory = new Factory();

        return factory;
    }

}
