package com.example.vusoft.mytodolist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Task implements Parcelable {

    public int id;
    public String subject;
    public String description;
    public long timestamp;
    public long modified;
    public long created;

    public static String getTableName(){
        return ModelManager.TASK_TABLE;
    }

    public Task() {
        super();
    }

    private Task(Parcel in) {
        super();
        this.setId(in.readInt());
        this.setSubject(in.readString());
        this.setDescription(in.readString());
        this.setTimestamp(in.readLong());
        this.setCreated(in.readLong());
        this.setModified(in.readLong());
    }

    public static Map getColumns(){
        Map<String, String> columns = new HashMap<String, String>();
        columns.put("id", "id");
        columns.put("subject", "subject");
        columns.put("description", "description");
        columns.put("timestamp", "timestamp");
        columns.put("modified", "modified");
        columns.put("created", "created");

        return columns;
    }

    public int getId() { return id;    }

    public void setId(int id) { this.id = id;    }

    public String getSubject() { return subject;    }

    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description;    }

    public void setDescription(String description) { this.description = description; }

    public long getTimestamp() { return timestamp;    }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public long getModified() { return modified;    }

    public void setModified(long modified) { this.modified = modified; }

    public long getCreated() { return created;    }

    public void setCreated(long created) { this.created = created; }

    @Override
    public String toString() {
        return "Task [id=" + id + ", subject=" + subject + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getSubject());
        parcel.writeString(getDescription());
        parcel.writeLong(getTimestamp());
        parcel.writeLong(getCreated());
        parcel.writeLong(getModified());
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}