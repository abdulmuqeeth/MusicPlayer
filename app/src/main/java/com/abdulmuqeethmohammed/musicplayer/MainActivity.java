package com.abdulmuqeethmohammed.musicplayer;

import android.app.ActionBar;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
        songsArrayList.add(new Songs("Payphone","Maroon 5", "https://en.wikipedia.org/wiki/Payphone_(song)","https://www.youtube.com/watch?v=KRaWnd3LJfs"));
        songsArrayList.add(new Songs("Bailamos","Enrique Iglesias", "https://en.wikipedia.org/wiki/Bailamos","https://www.youtube.com/watch?v=ixqOgtNERAQ"));
        songsArrayList.add(new Songs("Hips Don't Lie","Shakira", "https://en.wikipedia.org/wiki/Hips_Don%27t_Lie","https://www.youtube.com/watch?v=DUT5rEU6pqM"));
        songsArrayList.add(new Songs("Hey There Delilah","Plain White T's", "https://en.wikipedia.org/wiki/Hey_There_Delilah","https://www.youtube.com/watch?v=h_m-BjrxmgI"));
        songsArrayList.add(new Songs("If We Ever Meet Again","Timbaland", "https://en.wikipedia.org/wiki/If_We_Ever_Meet_Again","https://www.youtube.com/watch?v=KDKva-s_khY"));

        adapter = new SongAdapter(this, R.layout.listview_item, songsArrayList);
        mainList = (ListView) findViewById(R.id.main_list);
        mainList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainPageMenuInflater = getMenuInflater();
        mainPageMenuInflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

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

    private void optionAddClicked() {
        final Dialog addDialog = new Dialog(this);
        addDialog.setContentView(R.layout.add_song_dialog_window);
        addDialog.show();
        Window window = addDialog.getWindow();
        if (window != null) {
            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        }
    }

    private void optionDeleteClicked() {
        //TODO Delete song option functionality
    }
}
