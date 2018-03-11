package com.abdulmuqeethmohammed.musicplayer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Abdul Muqeeth Mohammed
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<Songs> songsArrayList;
    private ListView mainList;
    private Songs song;
    private SongAdapter adapter;
    private String addSongName;
    private String addArtistName;
    private String addSongWiki;
    private String addVideoUrl;
    private String addArtistWiki;

    private static final int OPTION_ADD_ID = -100;
    private static final int OPTION_DELETE_ID = -101;
    private static final int OPTION_EXIT_ID = -102;
    private static final int OPTION_ABOUT_ID = -103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res =getResources();

        String songName [] = res.getStringArray(R.array.SongName);
        String artistName [] = res.getStringArray(R.array.ArtistName);
        String songWiki [] = res.getStringArray(R.array.SongWiki);
        String videoUrl [] = res.getStringArray(R.array.VideoUrl);
        String artistWiki [] = res.getStringArray(R.array.ArtistWiki);

        songsArrayList = new ArrayList<Songs>();

        for (int i=0; i< songName.length ; i++) {
            songsArrayList.add(new Songs(songName[i], artistName[i], songWiki[i], videoUrl[i], artistWiki[i]));
        }

        adapter = new SongAdapter(this, R.layout.listview_item, songsArrayList);
        mainList = (ListView) findViewById(R.id.main_list);
        mainList.setAdapter(adapter);
        registerForContextMenu(mainList);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                playSong(position);
            }
        });
    }

    /*
    *Method called to inflate and display the context menu when any list item is long pressed.
    *The context menu displayed has play songs, view artist wiki and view song wiki options
    */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);
    }

    //Method called when an item from Context Menu is selected
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.context_play:
                playSong(info.position);
                return true;
            case R.id.context_song_wiki:
                viewSongWiki(info.position);
                return true;
            case R.id.context_artist_wiki:
                viewArtistWiki(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //Method to check if the activity should display an Options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        onPrepareOptionsMenu(menu);
        return true;
    }

    /*
    *This method is called every time the Menu option is selected
    *This method is used to populate the Menu and SubMenu dynamically
    */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.add(Menu.NONE, OPTION_ADD_ID, Menu.NONE, R.string.options_add);
        SubMenu deleteSubMenu = menu.addSubMenu(Menu.NONE,OPTION_DELETE_ID,Menu.NONE,R.string.options_delete);

        //Add Items to Submenu
        for (Songs song: songsArrayList)
        {
            deleteSubMenu.add(Menu.NONE,songsArrayList.indexOf(song),Menu.NONE, song.getSongTitle());
        }

        menu.add(Menu.NONE, OPTION_EXIT_ID, Menu.NONE, R.string.options_exit);
        menu.add(Menu.NONE, OPTION_ABOUT_ID, Menu.NONE, R.string.options_about);
        return super.onPrepareOptionsMenu(menu);
    }

    //Handling a Menu Item Click Event
    public boolean onOptionsItemSelected(MenuItem item) {

        //Delete song if it was selected from delete SubMenu
        songDelete(item);

        switch (item.getItemId()) {
            case OPTION_ADD_ID :
                optionAddClicked();
                return true;
            case OPTION_EXIT_ID :
                finish();
                System.exit(0);
                return true;
            case OPTION_ABOUT_ID :
                about();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Method to display Add Song Dialog Window
    private void optionAddClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_dialog_title);
        builder.setView(R.layout.add_song_dialog_window);

        final Dialog addSongDialog = builder.create();
        addSongDialog.show();

        Button add = (Button) addSongDialog.findViewById(R.id.dialog_add);
        Button dismiss = (Button) addSongDialog.findViewById(R.id.dialog_dismiss);

        /*
         * This method retrieves the data entered into the Add Songs Dialog and Encapsulates it into a Songs object
         * It also adds the Encapuslated Songs object the arraylist which gets passed to the adapter that is set on the ListView
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSongName = ((EditText) addSongDialog.findViewById(R.id.dialog_song_title)).getText().toString();
                addArtistName = ((EditText) addSongDialog.findViewById(R.id.dialog_artist_name)).getText().toString();
                addSongWiki = ((EditText) addSongDialog.findViewById(R.id.dialog_song_wiki)).getText().toString();
                addVideoUrl = ((EditText) addSongDialog.findViewById(R.id.dialog_video_url)).getText().toString();
                addArtistWiki = ((EditText) addSongDialog.findViewById(R.id.dialog_artist_wiki)).getText().toString();

                if (addSongName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Song Name", Toast.LENGTH_SHORT).show();
                }
                else if (addArtistName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Artist Name", Toast.LENGTH_SHORT).show();
                }
                else if (addVideoUrl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Video URL", Toast.LENGTH_SHORT).show();
                }
                else {
                    song =new Songs(addSongName, addArtistName, addSongWiki, addVideoUrl, addArtistWiki);
                    adapter.add(song);
                    addSongDialog.dismiss();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), addSongName+" added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Dismiss button OnClick Listener
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSongDialog.dismiss();
            }
        });

        //Resizing dialog box window
        Window window = addSongDialog.getWindow();
        if (window != null) {
            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        }
    }

    /*
    *   This method deletes a song form the list and updates the list
    *   This method takes the MenuItem as a parameter
    *   Checks if the item ID is one from the items in delete menu
    *   Deletes a song only if there are more than 1 items in the list
    */
    private void songDelete(MenuItem item) {
        try {
            if(adapter.getCount() > 1) {
                Songs toDelete = adapter.getItem((item.getItemId()));
                adapter.remove(toDelete);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Removed: "+toDelete.getSongTitle(), Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId()!= OPTION_DELETE_ID) {
                Toast.makeText(getApplicationContext(),"You should have atleast one song on the list", Toast.LENGTH_SHORT).show();
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            ;
        }
    }

    //Method to call VideoScreenActivity when play song option is selected
    private void playSong(int itemPosition) {
        song = (Songs) mainList.getItemAtPosition((itemPosition));
        Intent VideoScreenActivityIntent = new Intent(MainActivity.this, VideoScreen.class);
        VideoScreenActivityIntent.putExtra(getString(R.string.url_tag),song.getVideoUrl());
        startActivity(VideoScreenActivityIntent);
    }

    private void viewSongWiki(int itemPosition) {
        song = (Songs) mainList.getItemAtPosition((itemPosition));
        Intent VideoScreenActivityIntent = new Intent(MainActivity.this, VideoScreen.class);
        VideoScreenActivityIntent.putExtra(getString(R.string.url_tag),song.getSongWiki());
        startActivity(VideoScreenActivityIntent);
    }

    private void viewArtistWiki(int itemPosition) {
        song = (Songs) mainList.getItemAtPosition((itemPosition));
        Intent VideoScreenActivityIntent = new Intent(MainActivity.this, VideoScreen.class);
        VideoScreenActivityIntent.putExtra(getString(R.string.url_tag),song.getArtistWiki());
        startActivity(VideoScreenActivityIntent);
    }

    private void about() {
        Intent startAbout = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(startAbout);
    }
}
