package me.prankul.sms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.prankul.sms.R;
import me.prankul.sms.SmsHelper;
import me.prankul.sms.activities.ConversationActivity;

public class SMSAdapter extends  RecyclerView.Adapter<SMSAdapter.ViewHolder>
{
    public static String TAG = SMSAdapter.class.getSimpleName();

    Context mContext;
    Cursor  mCursor;

    public static int indexAddress,indexBody,indexRead, indexId, indexName, indexThreadId;

    public SMSAdapter(Activity context, Cursor cursor) {
        // TODO Auto-generated constructor stub
        this.mCursor  =cursor;
        this.mContext =context;

        indexId = cursor.getColumnIndex(SmsHelper.ID);
        indexAddress = cursor.getColumnIndex(SmsHelper.ADDRESS);
        indexBody = cursor.getColumnIndex(SmsHelper.BODY);
        indexRead=cursor.getColumnIndex(SmsHelper.READ);
        indexName=cursor.getColumnIndex(SmsHelper.NAME);
        indexThreadId=cursor.getColumnIndex(SmsHelper.THREAD_ID);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView tvName;
        TextView tvBody;
        CardView cardView;
        CardClickHandler mCardClickHandler;

        public ViewHolder(View v, CardClickHandler cardClickHandler) {
            super(v);
            mCardClickHandler=cardClickHandler;
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvBody = (TextView) v.findViewById(R.id.tv_body);
            cardView= (CardView) v.findViewById(R.id.cv);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Log.e(TAG,"Card Position: "+getPosition());
            mCardClickHandler.openThread(view,getPosition());
        }

        public interface CardClickHandler {
            void openThread(View view, int position);
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
                .inflate(R.layout.sms_card, parent, false);
        ViewHolder.CardClickHandler cardClickHandler= new ViewHolder.CardClickHandler() {
            @Override
            public void openThread(View view, int position) {
                Log.e(TAG, "View Position: " + position);

                mCursor.moveToPosition(position);
                String threadId=mCursor.getString(indexThreadId);
                String address=mCursor.getString(indexAddress);
                String name=mCursor.getString(indexName);

                Intent intent = new Intent(mContext, ConversationActivity.class);
                intent.putExtra(SmsHelper.THREAD_ID, threadId);
                intent.putExtra(SmsHelper.ADDRESS,address);
                intent.putExtra(SmsHelper.NAME,name);
                mContext.startActivity(intent);
            }
        };

        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view, cardClickHandler);
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