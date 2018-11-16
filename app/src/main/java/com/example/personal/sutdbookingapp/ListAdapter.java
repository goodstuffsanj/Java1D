package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private static final String TAG = "ListAdapter";

    private ArrayList<Bookable> bookables = new ArrayList<>();
    private Context context;
    public final static String FACIL_ID = "FACIL_ID";
    public final static String PROF_ID = "PROF_ID";

    public ListAdapter(ArrayList<Bookable> bookables, Context context) {
        this.bookables = bookables;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        final Bookable bookable = bookables.get(i);
        Glide.with(context).load(bookable.getImage()).into(viewHolder.image);
        viewHolder.image_name.setText(bookable.getName());
        viewHolder.list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick: clicked on: "+ bookable.getName());
                if (context.getClass()==BookFacilities.class) {
                    Intent intent = new Intent(context, Facility.class);
                    intent.putExtra(FACIL_ID, bookable.getName());
                    context.startActivity(intent);
                }
                else if (context.getClass()==BookProf.class) {
                    Intent intent = new Intent(context, Prof.class);
                    intent.putExtra(PROF_ID, bookable.getName());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookables.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView image_name;
        RelativeLayout list_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            image_name = itemView.findViewById(R.id.image_name);
            list_layout = itemView.findViewById(R.id.list_layout);
        }
    }

}
