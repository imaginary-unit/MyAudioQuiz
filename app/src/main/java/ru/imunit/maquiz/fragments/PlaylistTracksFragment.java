package ru.imunit.maquiz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.imunit.maquiz.R;
import ru.imunit.maquiz.models.IPlaylistModel;
import ru.imunit.maquiz.models.PlaylistModel;
import ru.imunit.maquiz.views.adapters.PlaylistRecyclerAdapter;
import ru.imunit.maquizdb.entities.DBTrack;


public class PlaylistTracksFragment extends Fragment implements
        PlaylistModel.ModelUpdateListener,
        PlaylistRecyclerAdapter.ItemClickListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecycler;
    private PlaylistRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mRecyclerLayout;
    private boolean mShowBlackList;
    private IPlaylistModel mModel;

    public PlaylistTracksFragment() {
        mShowBlackList = false;
    }

    public void setShowBlackList(boolean state) {
        mShowBlackList = state;
    }

    public void setModel(IPlaylistModel model) {
        mModel = model;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_viewer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler = (RecyclerView)getView().findViewById(R.id.recycler);
        mRecyclerLayout = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mRecyclerLayout);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDataUpdated() {
        List<DBTrack> tracks;
        if (mShowBlackList)
            tracks = mModel.getBlackList();
        else
            tracks = mModel.getAllTracks();
        mRecyclerAdapter = new PlaylistRecyclerAdapter(tracks);
        mRecyclerAdapter.setOnClickListener(this);
        int menuMode = mShowBlackList ? PlaylistRecyclerAdapter.MENU_BLACK_LIST :
                PlaylistRecyclerAdapter.MENU_ALL_TRACKS;
        mRecyclerAdapter.setMenuMode(menuMode);
        mRecycler.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onBlacklistTrack(DBTrack track) {
        mListener.onBlacklistTrack(track);
    }

    @Override
    public void onClearBlackList() {
        mListener.onClearBlackList();
    }

    public interface OnFragmentInteractionListener {
        void onBlacklistTrack(DBTrack track);
        void onClearBlackList();
    }
}
