package com.abdulmuqeethmohammed.musicplayer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Songs> songsArrayList;
    private ListView mainList;
    private Songs song;
    private SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsArrayList = new ArrayList<Songs>();
        songsArrayList.add(new Songs("Payphone","Maroon 5", "https://en.wikipedia.org/wiki/Payphone_(song)","https://www.youtube.com/watch?v=KRaWnd3LJfs", "https://en.wikipedia.org/wiki/Maroon_5"));
        songsArrayList.add(new Songs("Bailamos","Enrique Iglesias", "https://en.wikipedia.org/wiki/Bailamos","https://www.youtube.com/watch?v=ixqOgtNERAQ", "https://en.wikipedia.org/wiki/Enrique_Iglesias"));
        songsArrayList.add(new Songs("Hips Don't Lie","Shakira", "https://en.wikipedia.org/wiki/Hips_Don%27t_Lie","https://www.youtube.com/watch?v=DUT5rEU6pqM", "https://en.wikipedia.org/wiki/Shakira"));
        songsArrayList.add(new Songs("Hey There Delilah","Plain White T's", "https://en.wikipedia.org/wiki/Hey_There_Delilah","https://www.youtube.com/watch?v=h_m-BjrxmgI", "https://en.wikipedia.org/wiki/Plain_White_T%27s"));
        songsArrayList.add(new Songs("If We Ever Meet Again","Timbaland", "https://en.wikipedia.org/wiki/If_We_Ever_Meet_Again","https://www.youtube.com/watch?v=KDKva-s_khY", "https://en.wikipedia.org/wiki/Timbaland"));

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

    //Method to inflate the options menu with Add, Delete and Exit Options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainPageMenuInflater = getMenuInflater();
        mainPageMenuInflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

    //Handling a Menu Item Click Event
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_add :
                optionAddClicked();
                return true;
            case R.id.option_delete :
                optionDeleteClicked();
                return true;
            case R.id.option_exit :
                this.finishAndRemoveTask();
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

        //Add Button OnCLick Listener
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add Song Logic
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

    private void optionDeleteClicked() {
        //TODO Delete song option functionality
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
}
