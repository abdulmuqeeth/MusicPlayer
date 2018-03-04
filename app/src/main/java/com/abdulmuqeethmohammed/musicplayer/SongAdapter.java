package com.abdulmuqeethmohammed.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abdul Muqeeth Mohammed
 */

public class SongAdapter extends ArrayAdapter<Songs> {

    private ArrayList<Songs> songList;
    private int layoutResourceId;
    private Context mContext;

    public SongAdapter (Context context, int layoutResourceId, ArrayList<Songs> songList) {
        super(context, R.layout.listview_item, songList);
        this.layoutResourceId = layoutResourceId;
        this.songList = songList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        SongHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent, false);
            holder = new SongHolder();
            holder.songTitle = (TextView)row.findViewById(R.id.album_name);
            holder.artist = (TextView)row.findViewById(R.id.artist_name);

            row.setTag(holder);
        }
        else {
            holder = (SongHolder) row.getTag();
        }

        Songs song = songList.get(position);
        holder.songTitle.setText(song.getSongTitle());
        holder.artist.setText(song.getArtistName());

        return row;
    }

    static class SongHolder {
        TextView songTitle;
        TextView artist;
    }
}
