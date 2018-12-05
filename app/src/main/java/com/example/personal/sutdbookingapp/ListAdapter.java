package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable{

    private static final String TAG = "ListAdapter";

    private ArrayList<Bookable> bookables = new ArrayList<>();
    private ArrayList<Bookable> copies = new ArrayList<>();
    private Context context;
    public final static String NAME = "NAME";
    public final static String IMAGE = "IMAGE";
    public final static String LOCATION = "LOCATION";
    public final static String EMAIL = "EMAIL";
    public final static String CONTACT ="CONTACT";
    public final static String DESCRIPTION = "DESCRIPTION";
    public final static String BLOCKED_TIMINGS = "BLOCKED_TIMINGS";

    public ListAdapter(ArrayList<Bookable> bookables, Context context) {
        this.bookables = bookables;
        this.context = context;
        copies = new ArrayList<>(bookables);
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
                if (context.getClass() == BookFacilities.class) {
                    Intent intent = new Intent(context, Facility.class);
                    intent.putExtra(NAME, bookable.getName());
                    intent.putExtra(IMAGE, bookable.getImage());
                    intent.putExtra(LOCATION, bookable.getLocation());
                    intent.putExtra(DESCRIPTION, bookable.getDescription());
                    intent.putExtra(BLOCKED_TIMINGS, (ArrayList) bookable.getBlockedTimings());
                    context.startActivity(intent);
                }
                else if (context.getClass() == BookProf.class) {
                    Intent intent = new Intent(context, Prof.class);
                    intent.putExtra(NAME, bookable.getName());
                    intent.putExtra(IMAGE, bookable.getImage());
                    intent.putExtra(LOCATION, bookable.getLocation());
                    intent.putExtra(EMAIL, bookable.getEmail());
                    intent.putExtra(CONTACT, bookable.getContact());
                    intent.putExtra(DESCRIPTION, bookable.getDescription());
                    intent.putExtra(BLOCKED_TIMINGS, (ArrayList) bookable.getBlockedTimings());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookables.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

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
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Bookable> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length()==0) {
                filtered.addAll(copies);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Bookable item: copies) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filtered.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bookables.clear();
            bookables.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
