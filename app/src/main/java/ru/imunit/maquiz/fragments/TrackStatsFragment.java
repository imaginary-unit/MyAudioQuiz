package ru.imunit.maquiz.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.imunit.maquiz.R;
import ru.imunit.maquiz.models.IStatsModel;
import ru.imunit.maquiz.models.StatsModel;
import ru.imunit.maquiz.views.adapters.StatsRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrackStatsListener} interface
 * to handle interaction events.
 */
public class TrackStatsFragment extends Fragment implements
        StatsModel.ModelUpdateListener {

    public interface TrackStatsListener {
        void onTrackStatsInitialized();
    }

    public TrackStatsFragment() {
        // Required empty public constructor
    }

    public void setModel(IStatsModel model) {
        mModel = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("TrackStats", "DBG CV");
        return inflater.inflate(R.layout.fragment_track_stats, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TrackStatsListener) {
            mListener = (TrackStatsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameStatsListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mListener != null)
            mListener.onTrackStatsInitialized();
        Log.i("TrackStats", "DBG View created");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onUpdateStarted() {

    }

    @Override
    public void onUpdateCompleted() {
        RecyclerView rv = (RecyclerView)getView().findViewById(R.id.tracksRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        StatsRecyclerAdapter adapter = new StatsRecyclerAdapter(getContext(), mModel.getTracks());
        rv.setAdapter(adapter);
    }

    private TrackStatsListener mListener;
    private IStatsModel mModel;
}
