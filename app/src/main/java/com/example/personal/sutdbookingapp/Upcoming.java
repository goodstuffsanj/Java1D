package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Upcoming.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Upcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Upcoming extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TIME = "TIME";
    private static final String STATUS = "STATUS";
    private static final String IS_PROF = "IS_PROF";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<BookingInstance> upcomings = new ArrayList<>();
    private String mTime;
    private String mStatus;
    private Boolean mIsProf;

    private OnFragmentInteractionListener mListener;

    public Upcoming() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param timing get timing of booking
     * @param status get status of booking
     * @param isProf check if booking is prof (not facility)
     * @return A new instance of fragment Upcoming.
     */
    // TODO: Rename and change types and number of parameters
    public static Upcoming newInstance(String timing, String status, Boolean isProf) {
        Upcoming fragment = new Upcoming();
        Bundle args = new Bundle();
        args.putString(TIME, timing);
        args.putString(STATUS, status);
        args.putBoolean(IS_PROF, isProf);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTime = getArguments().getString(TIME);
            mStatus = getArguments().getString(STATUS);
            mIsProf = getArguments().getBoolean(IS_PROF);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("BookingInstance", "OnCreateView");
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        for (int i=0; i<20; i++) {
            BookingInstance bookingInstance = new BookingInstance("120839201","Professor "+Integer.toString(i+1),"12/11/2018","08:30","09:30","Building 1 lvl 5","https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg","upcoming");
            upcomings.add(bookingInstance);
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        BookingInstanceAdapter adapter = new BookingInstanceAdapter(this.getContext(), upcomings);
        recyclerView.setLayoutManager(new LinearLayoutManager(Upcoming.this.getActivity()));
        recyclerView.setAdapter(adapter);
        return  rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
