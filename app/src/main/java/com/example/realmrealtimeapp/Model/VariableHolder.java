package com.example.realmrealtimeapp.Model;

import android.widget.EditText;

public class VariableHolder {
    private static int editId;

    private  static EditText taskName;

    public static int getEditId() {
        return editId;
    }

    public static void setEditId(int editId) {
        VariableHolder.editId = editId;
    }

    public static EditText getTaskName() {
        return taskName;
    }

    public static void setTaskName(EditText taskName) {
        VariableHolder.taskName = taskName;
    }
}
