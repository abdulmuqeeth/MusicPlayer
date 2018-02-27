package com.abdulmuqeethmohammed.musicplayer;

/**
 * Created by Abdul Muqeeth Mohammed
 */

public class Songs {
    private String songTitle;
    private String artistName;
    private String songWiki;
    private String videoUrl;
    private String artistWiki;

    public Songs(String songTitle, String artistName, String songWiki, String videoUrl, String artistWiki) {
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.songWiki = songWiki;
        this.videoUrl = videoUrl;
        this.artistWiki = artistWiki;
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
    public String getArtistWiki() {
        return artistWiki;
    }
}
