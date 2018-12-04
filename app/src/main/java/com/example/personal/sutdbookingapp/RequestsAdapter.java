package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjayshankar on 24/11/18.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {

    private static final String TAG = "RequestsAdapter";

    //private List RequestsData;
    private ArrayList<RequestsData> RequestsData = new ArrayList<>();
    private Context context;

    public RequestsAdapter(Context context, ArrayList<RequestsData> RequestsData) {
        this.RequestsData = RequestsData;
        this.context = context;
    }

    @Override
    public RequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_requests_item,
                parent, false);
        RequestsViewHolder holder = new RequestsViewHolder(view);
        return holder;
        //return new RequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestsViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder() is called");  //for every item that is in the list

        holder.senderName.setText(RequestsData.get(position).getSenderName());
        holder.date.setText(RequestsData.get(position).getDate());
        holder.reason.setText(RequestsData.get(position).getReason());

        holder.tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestsData.remove(position);
                Toast.makeText(context, "request has been accepted", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });

        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestsData.remove(position);
                Toast.makeText(context, "request has been declined", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });

//        holder.senderName.setText(RequestsData.get(position).getSenderName());
//        holder.date.setText(RequestsData.get(position).getDate());
//        holder.reason.setText(RequestsData.get(position).getReason());
//
//
//
//        holder.tick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "request has been accepted", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        holder.cross.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "request has been declined", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return RequestsData.size();
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout request;

        TextView senderName;
        ImageButton tick;
        ImageButton cross;
        TextView date;
        TextView reason;

        public RequestsViewHolder(View itemView) {
            super(itemView);

            request = itemView.findViewById(R.id.request);
            senderName = itemView.findViewById(R.id.requestName);
            tick = itemView.findViewById(R.id.tick);
            cross = itemView.findViewById(R.id.cross);
            date = itemView.findViewById(R.id.requestTime);
            reason = itemView.findViewById(R.id.requestReason);
        }
    }
}

