package com.example.vusoft.mytodolist;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vulukoylu on 1/2/2016.
 */
public class TaskUtil {
    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    public static final SimpleDateFormat formatter = new SimpleDateFormat(
            "MM/dd/yyyy", Locale.ENGLISH);

    public static String getDateDefault(long time){
        return getDate(time, DEFAULT_DATE_FORMAT);
    }

    public static String getDate(long time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(new Date(time));
    }

    public static long strToTime(String str){
        try{
            Date date = formatter.parse(str);
            return date.getTime();
        }
        catch (Exception ex){
            return 0;
        }
    }

    public static long getCurrentTime(){
        return System.currentTimeMillis();
    }


}
