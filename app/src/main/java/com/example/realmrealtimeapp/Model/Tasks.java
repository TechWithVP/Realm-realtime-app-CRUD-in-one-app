package com.example.realmrealtimeapp.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tasks extends RealmObject {
    @PrimaryKey
    private int task_id;

    private String task_name;

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Tasks() {
    }

    public Tasks(String task_name) {
        this.task_name = task_name;
    }
}
