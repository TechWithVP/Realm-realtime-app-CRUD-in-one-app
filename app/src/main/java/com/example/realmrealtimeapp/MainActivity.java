package com.example.realmrealtimeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmrealtimeapp.Model.Tasks;
import com.example.realmrealtimeapp.Model.VariableHolder;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    EditText taskName;
    Button insertBtn, updateBtn;
    RecyclerView rv;
    MyRecyclerViewAdapter adapter;
    RealMHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Realm.init(getApplicationContext());
        helper = new RealMHelper();
        VariableHolder.setEditId(-1);

        taskName = findViewById(R.id.taskName);
        VariableHolder.setTaskName(taskName);

        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        rv = findViewById(R.id.rv);
        updateRV();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_name = taskName.getText().toString();

                helper.insertData(new Tasks(task_name));
                taskName.setText("");
                updateRV();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_name = taskName.getText().toString().trim();
//                Toast.makeText(MainActivity.this, String.valueOf(task_name.isEmpty()), Toast.LENGTH_SHORT).show();
                if(task_name.isEmpty() != true){
                    helper.editData(VariableHolder.getEditId(), task_name);
                    taskName.setText("");
                    updateRV();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("No data to UPDATE, please select item from list below.").setTitle("Message");
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    public void updateRV() {
        adapter = new MyRecyclerViewAdapter((OrderedRealmCollection<Tasks>) helper.getData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}