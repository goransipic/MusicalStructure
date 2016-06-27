package com.example.android.uamp.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.uamp.Config;
import com.example.android.uamp.R;
import com.example.android.uamp.model.Track;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by goransi on 27.6.2016..
 */
public class PlaybackControlsActivity extends BaseActivity {

    ImageView mCoverImage;
    TextView mTitle;
    TextView mDescription;
    ImageView mControls;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_playback_controls);

        mCoverImage = (ImageView) findViewById(R.id.album_cover);
        mTitle = (TextView) findViewById(R.id.title);
        mDescription = (TextView) findViewById(R.id.artist);
        mControls = (ImageView) findViewById(R.id.play_pause);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Track track = getIntent().getParcelableExtra("Track");

        getSupportActionBar().setTitle(track.getTitle());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Picasso.with(this).load(track.getArtworkURL()).into(mCoverImage);
        mTitle.setText(track.getTitle());
        mDescription.setText(track.getDescription());

        mControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePlayPause();
            }
        });

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mControls.setImageResource(R.drawable.ic_play_arrow_black_36dp);
            }
        });


        try {
            mMediaPlayer.setDataSource(track.getStreamURL() + "?client_id=" + Config.CLIENT_ID);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void togglePlayPause() {

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mControls.setImageResource(R.drawable.ic_play_arrow_black_36dp);
        } else {
            mMediaPlayer.start();
            mControls.setImageResource(R.drawable.ic_pause_black_36dp);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


}
