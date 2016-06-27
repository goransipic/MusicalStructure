package com.example.android.uamp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.uamp.R;
import com.example.android.uamp.adapter.SCTrackAdapter;
import com.example.android.uamp.datasource.SCService;
import com.example.android.uamp.datasource.SoundCloudService;
import com.example.android.uamp.model.Track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by goransi on 27.6.2016..
 */
public class MusicPlayerActivity extends BaseActivity {

    private SCTrackAdapter mBrowserAdapter;
    private SCService mScService;
    private ListView mListView;
    private List<Track> mListItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mListItems = new ArrayList<Track>();

        mScService = SoundCloudService.getService();

        mScService.getRecentTracks(new SimpleDateFormat(getString(R.string.date_format)).format(new Date()), new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                loadTracks(tracks);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        mBrowserAdapter = new SCTrackAdapter(this, mListItems);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mBrowserAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicPlayerActivity.this, PlaybackControlsActivity.class);

                intent.putExtra("Track",mListItems.get(i));

                MusicPlayerActivity.this.startActivity(intent);

            }
        });

    }

    private void loadTracks(List<Track> tracks) {
        mListItems.clear();
        mListItems.addAll(tracks);

        mBrowserAdapter.notifyDataSetChanged();

    }
}
