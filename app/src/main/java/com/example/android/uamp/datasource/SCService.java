package com.example.android.uamp.datasource;

import com.example.android.uamp.Config;
import com.example.android.uamp.model.Track;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by goransi on 27.6.2016..
 */
public interface SCService {

    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    public void getRecentTracks(@Query("created_at[from]") String date, Callback<List<Track>> cb);

}
