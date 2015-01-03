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

import com.prankul.model.Comment;
import com.prankul.model.Task;
import com.prankul.todo.R;

public class CommentListAdapter extends BaseAdapter 
{
    private Activity activity;
    private LayoutInflater inflater;
    private Vector<Comment> commentVector;
    public CommentListAdapter(Activity activity, Vector<Comment> commentVector) 
    {
        this.activity = activity;
        this.commentVector = commentVector;
    }
 
    @Override
    public int getCount() {
        return commentVector.size();
    }
 
    @Override
    public Object getItem(int location) {
        return commentVector.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
 
        if (inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.comment_row, null);
        TextView commentTxt = (TextView) convertView.findViewById(R.id.commentTxt);
        TextView createdTxt = (TextView) convertView.findViewById(R.id.createdAtTxt);
        Comment comment = commentVector.get(position);
        commentTxt.setText(comment.getComment());
        createdTxt.setText(comment.getCreated_at());
        return convertView;
    }
 
}