package com.abdulmuqeethmohammed.musicplayer;

/**
 * Created by Abdul Muqeeth Mohammed
 */

public class Songs {
    private String songTitle;
    private String artistName;
    private String songWiki;
    private String videoUrl;

    public Songs(String songTitle, String artistName, String songWiki, String videoUrl) {
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.songWiki = songWiki;
        this.videoUrl = videoUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }
    public String getArtistName() {
        return artistName;
    }
    public String getSongWiki() {
        return songWiki;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
}
