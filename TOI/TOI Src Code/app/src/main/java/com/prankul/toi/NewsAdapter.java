package com.prankul.toi;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    Context mContext;
    ArrayList newsItemList;
    boolean offline;

    public NewsAdapter(Activity context, ArrayList arrayList, boolean isOffline) {
        // TODO Auto-generated constructor stub
        this.mContext =context;
        this.newsItemList=arrayList;
        this.offline=isOffline;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView thumbnailView;
        TextView headline;
        TextView byLine;
        TextView dateLine;

        public ViewHolder(View v) {
            super(v);
            thumbnailView=(ImageView) v.findViewById(R.id.thumbnail);
            headline=(TextView) v.findViewById(R.id.headline);
            byLine=(TextView) v.findViewById(R.id.by_line);
            dateLine = (TextView) v.findViewById(R.id.date_line);
        }
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }


    //gets called for each card i.e. set data here
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // TODO Auto-generated method stub

        NewsItem newsItem= (NewsItem) newsItemList.get(position);
        viewHolder.headline.setText(newsItem.getHeadLine());

        if(newsItem.getImage() != null && newsItem.getImage().getThumb()!=null){
            Picasso.with(mContext)
                    .load(newsItem.getImage().getThumb())
                    .networkPolicy(offline ? NetworkPolicy.OFFLINE : NetworkPolicy.NO_CACHE)
                    .into(viewHolder.thumbnailView);
        }

        long diff=Utils.getTimeDifference(newsItem.getDateLine());
        if(diff<60){
            viewHolder.byLine.setText(newsItem.getByLine());
            viewHolder.dateLine.setText(diff+" minutes ago" );
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

}