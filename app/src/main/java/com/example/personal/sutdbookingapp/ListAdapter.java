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
import java.util.ListIterator;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private static final String TAG = "ListAdapter";
    private ArrayList<ListItem> listOfItems;
    private Context context;
    public final static String FACIL_ID = "FACIL_ID";
    public final static String PROF_ID = "PROF_ID";
    public ListAdapter(ArrayList<ListItem> list, Context context) {
        this.listOfItems = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        String image = listOfItems.get(i).image;
        String name = listOfItems.get(i).name;
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context).load(image).into(viewHolder.image);
        viewHolder.image_name.setText(name);
        viewHolder.list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = listOfItems.get(i).name;
                Log.d(TAG, "onclick: clicked on: "+ name);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                if (context.getClass()==BookProf.class) {
                    Intent intent = new Intent(context, Facility.class);
                    intent.putExtra(FACIL_ID, name);
                    context.startActivity(intent);
                }
                else if (context.getClass()==BookFacilities.class) {
                    Intent intent = new Intent(context, Prof.class);
                    intent.putExtra(PROF_ID, name);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
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

    public ArrayList<String>[] toNameImage(){
        ArrayList<String>[] ans = new ArrayList[2];
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i <listOfItems.size(); i ++ ) {
            names.add(listOfItems.get(i).name);
            images.add(listOfItems.get(i).image);
        }
        ans[0] = names;
        ans[1] = images;
        return ans;

    }


    static public class ListItem{
        public String name;
        public String image;
        public ListItem(String name, String image) {
            this.name = name;
            this.image = image;
        }
    }


}
