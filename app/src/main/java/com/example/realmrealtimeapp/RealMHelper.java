package com.example.realmrealtimeapp;

import android.widget.ListView;

import com.example.realmrealtimeapp.Model.Tasks;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class RealMHelper {
    Realm realm = Realm.getDefaultInstance();

    public void insertData(final Tasks t){
        Number num = realm.where(Tasks.class).max("task_id");
        final int nextID;
        if(num == null){
            nextID = 0;
        }else{
            nextID = num.intValue() + 1;
        }

        t.setTask_id(nextID);
        realm.beginTransaction();
        realm.copyToRealm(t);
        realm.commitTransaction();
    }

    public List<Tasks> getData(){
        return realm.where(Tasks.class).sort("task_id", Sort.DESCENDING).findAll();
    }

    public void editData(final int id, final String task_name){
        final Tasks item = realm.where(Tasks.class).equalTo("task_id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.setTask_name(task_name);
                realm.copyFromRealm(item);
            }
        });
    }

    public void deleteData(final int id){
        final Tasks item = realm.where(Tasks.class).equalTo("task_id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.deleteFromRealm();
            }
        });
    }
}
