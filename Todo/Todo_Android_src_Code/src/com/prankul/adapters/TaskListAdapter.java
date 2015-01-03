package com.prankul.adapters;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.prankul.model.Task;
import com.prankul.todo.R;

public class TaskListAdapter extends BaseAdapter 
{
    private Activity activity;
    private LayoutInflater inflater;
    private Vector<Task> taskVector;
    public TaskListAdapter(Activity activity, Vector<Task> taskVector) 
    {
        this.activity = activity;
        this.taskVector = taskVector;
    }
 
    @Override
    public int getCount() {
        return taskVector.size();
    }
 
    @Override
    public Object getItem(int location) {
        return taskVector.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.task_row, null);
        ImageView statusIcon=(ImageView)convertView.findViewById(R.id.status_icon);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        Task task = taskVector.get(position);
        title.setText(task.getTitle());
        status.setText(task.getStatus());
        return convertView;
    }

	
 
}