package com.example.android.uamp.datasource;

import com.example.android.uamp.Config;

import retrofit.RestAdapter;

/**
 * Created by goransi on 27.6.2016..
 */
public class SoundCloudService {

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    private static final SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }

}
