package me.prankul.sms;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InboxAdapter extends  RecyclerView.Adapter<InboxAdapter.ViewHolder>
{
    Context mContext;
    Cursor  mCursor;

    public static int indexAddress,indexBody,indexRead, indexId, indexName;

    public InboxAdapter(Activity context, Cursor cursor) {
        // TODO Auto-generated constructor stub
        this.mCursor  =cursor;
        this.mContext =context;

        indexId = cursor.getColumnIndex(SmsHelper.ID);
        indexAddress = cursor.getColumnIndex(SmsHelper.ADDRESS);
        indexBody = cursor.getColumnIndex(SmsHelper.BODY);
        indexRead=cursor.getColumnIndex(SmsHelper.READ);
        indexName=cursor.getColumnIndex(SmsHelper.NAME);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvName;
        TextView tvBody;
        CardView cardView;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvBody = (TextView) v.findViewById(R.id.tv_body);
            cardView= (CardView) v.findViewById(R.id.cv);
        }
    }

    @Override
    public int getItemCount() {
        return (mCursor == null) ? 0 : mCursor.getCount();
    }


    //gets called for each card i.e. set data here
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // TODO Auto-generated method stub
        mCursor.moveToPosition(position);

        //Set Contact Name/Number
        if(mCursor.getString(indexName)!=null){
           viewHolder.tvName.setText(mCursor.getString(indexName));
           viewHolder.cardView.setCardBackgroundColor(Color.WHITE);
        }
        else{
           viewHolder.tvName.setText(mCursor.getString(indexAddress));
           viewHolder.cardView.setCardBackgroundColor(Color.LTGRAY);
        }

        //Check message is read or not
        if(mCursor.getString(indexRead).equals("0"))
            viewHolder.tvBody.setTypeface(Typeface.create(viewHolder.tvBody.getTypeface(), Typeface.BOLD));
        else
            viewHolder.tvBody.setTypeface(Typeface.create(viewHolder.tvBody.getTypeface(), Typeface.NORMAL));

        //Set message body
        viewHolder.tvBody.setText(mCursor.getString(indexBody));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inbox_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public Cursor swapCursor(Cursor cursor) {
        if (mCursor == cursor) {
            return null;
        }
        Cursor oldCursor = mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }
}