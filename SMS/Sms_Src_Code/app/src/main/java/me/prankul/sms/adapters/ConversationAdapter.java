package me.prankul.sms.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.prankul.sms.R;
import me.prankul.sms.SmsHelper;

public class ConversationAdapter extends  RecyclerView.Adapter<ConversationAdapter.ViewHolder>
{
    public static String TAG = ConversationAdapter.class.getSimpleName();

    Context mContext;
    Cursor  mCursor;

    public static int indexId,indexBody, indexType, indexDate;

    public ConversationAdapter(Activity context, Cursor cursor) {
        // TODO Auto-generated constructor stub
        this.mCursor  =cursor;
        this.mContext =context;

        indexId = cursor.getColumnIndex(SmsHelper.ID);
        indexBody = cursor.getColumnIndex(SmsHelper.BODY);
        indexType = cursor.getColumnIndex(SmsHelper.TYPE);
        indexDate = cursor.getColumnIndex(SmsHelper.DATE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        TextView tvBody;
        CardView cardView;

        public ViewHolder(View v) {
            super(v);
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

        //Set message body
        viewHolder.tvBody.setText(mCursor.getString(indexBody));

        //Set card as left or right
        String messageType=mCursor.getString(indexType);
        if(messageType.equals(""+SmsHelper.MESSAGE_TYPE_INBOX)){
            viewHolder.cardView.setCardBackgroundColor(Color.GREEN);
        }
        else{
            viewHolder.cardView.setCardBackgroundColor(Color.YELLOW);
        }
        viewHolder.cardView.setClipToPadding(true);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversation_card, parent, false);

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