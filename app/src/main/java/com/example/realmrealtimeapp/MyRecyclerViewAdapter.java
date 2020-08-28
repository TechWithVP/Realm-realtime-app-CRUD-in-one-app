package com.example.realmrealtimeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmrealtimeapp.Model.Tasks;
import com.example.realmrealtimeapp.Model.VariableHolder;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MyRecyclerViewAdapter extends RealmRecyclerViewAdapter<Tasks, MyRecyclerViewAdapter.MyViewHolder> {
    OrderedRealmCollection<Tasks> data;

    public MyRecyclerViewAdapter(@Nullable OrderedRealmCollection<Tasks> data) {
        super(data, true);
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Tasks tasks = getItem(position);
        holder.idTV.setText(String.valueOf(tasks.getTask_id()));
        holder.tasknameTV.setText(tasks.getTask_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VariableHolder.getTaskName().setText(data.get(position).getTask_name());
                VariableHolder.setEditId(data.get(position).getTask_id());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                RealMHelper helper = new RealMHelper();
                helper.deleteData(data.get(position).getTask_id());
                return true;
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getTask_id();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idTV, tasknameTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            tasknameTV = itemView.findViewById(R.id.tasknameTV);
        }
    }
}
