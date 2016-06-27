package com.example.android.uamp.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.uamp.R;
import com.example.android.uamp.model.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by goransi on 27.6.2016..
 */
public class SCTrackAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Track> mTracks;

    public SCTrackAdapter(Context context, List<Track> tracks) {
        mContext = context;
        mTracks = tracks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Track track = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.media_list_item, parent, false);
            holder = new ViewHolder();
            holder.trackImageView = (ImageView) convertView.findViewById(R.id.play_eq);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
            holder.descriptionTextView = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(track.getTitle())) {
            holder.titleTextView.setText(track.getTitle());
        }
        if (!TextUtils.isEmpty(track.getDescription())) {
            holder.descriptionTextView.setText(track.getDescription());
        }

        holder.trackImageView.clearColorFilter();
        // Trigger the download of the URL asynchronously into the image view.
        if (track.getArtworkURL() != null) {
            Picasso.with(mContext).load(track.getArtworkURL()).into(holder.trackImageView);
        } else {
            holder.trackImageView.setColorFilter(mContext.getResources().getColor(R.color.media_item_icon_not_playing));
            holder.trackImageView.setImageResource(R.drawable.ic_play_arrow_black_36dp);

        }
        return convertView;

    }

    @Override
    public int getCount() {
        return mTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return mTracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView trackImageView;
        TextView titleTextView;
        TextView descriptionTextView;
    }

}