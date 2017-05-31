package com.alexurangareyes.mysqlite.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexurangareyes.mysqlite.R;
import com.alexurangareyes.mysqlite.adapter.placesAdapter;
import com.alexurangareyes.mysqlite.model.DataBaseManager;
import com.alexurangareyes.mysqlite.model.Place;

import java.util.ArrayList;

/**
 * Created by alexurangareyes on 5/31/17.
 */

public class FavoritesFragment extends Fragment {


    public DataBaseManager manager;
    public Cursor mCursor;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public placesAdapter mAdapter;

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        manager = new DataBaseManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.places_recycler_view);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mCursor = manager.loadCursorPlacesFav();

        //Log.i("myTag", "mCursor.getCount() = " + String.valueOf(mCursor.getCount()));

        //Log.i("myTag", "manager.getProfilesCount() = " + String.valueOf(manager.getProfilesCount()));


        ArrayList<Place> mArrayList = new ArrayList<>();

        while (mCursor.moveToNext()) {

            int index = mCursor.getColumnIndex(DataBaseManager.CN_ID);
            int index2 = mCursor.getColumnIndex(DataBaseManager.CN_NAME);
            int index3 = mCursor.getColumnIndex(DataBaseManager.CN_STATE);

            int cid = mCursor.getInt(index);
            String name = mCursor.getString(index2);
            String state = mCursor.getString(index3);

            //Place bean = new Place(cid, name);
            Place place = new Place();
            place.setId(cid);
            place.setName(name);
            place.setState(state);

            mArrayList.add(place);
        }


        ////mAdapter = new placesAdapter(getActivity(), manager.getAllData());

        mAdapter = new placesAdapter(getActivity(),mArrayList);

        mRecyclerView.setAdapter(mAdapter);

        return v;

    }

}
