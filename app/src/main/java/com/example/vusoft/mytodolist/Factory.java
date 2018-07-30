package com.example.vusoft.mytodolist;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.reflect.*;

/**
 * Created by vulukoylu on 12/28/2015.
 */
public class Factory {

    public Task createTask(Map<String, ?> config){
        return (Task) createObject(ModelManager.TASK_CLASS, config);
    }

    public Object createObject(String className, Map<String, ?> config){
        return assignAttributes(createInstance(className), config);
    }

    public Object assignAttributes(Object object, Map<String, ?> attributes){
        if (attributes == null || attributes.size()== 0) return object;

        Map<String, String> fieldNames = getFieldNamesByObject(object);

        for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
            if (attributes.containsKey(entry.getKey())){
                try {
                    object.getClass().getDeclaredField(entry.getKey()).set(object, attributes.get(entry.getKey()));
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }

        return object;
    }

    public Object createInstance(String className){
        Object object = null;
        try {
            Class classDefinition = Class.forName(className);
            object = classDefinition.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return object;
    }

    public Map<String, String> getFieldNamesByObject(Object object){
        Map<String, String> fieldNames = new HashMap<String, String>();
        Field[] fields = object.getClass().getFields();
        for (Field field: fields){
            fieldNames.put(field.getName(), field.getName());
        }

        return fieldNames;
    }


}
