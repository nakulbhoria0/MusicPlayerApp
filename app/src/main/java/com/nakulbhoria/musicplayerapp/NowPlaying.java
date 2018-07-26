package com.nakulbhoria.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class NowPlaying extends AppCompatActivity{

    ImageButton play, fastForward, fastRewind, shuffle, repeat, skipNext, previous;
    TextView songNameNow, artistNameNow;
    private static final String TAG = "Now Playing Activity";
    ArrayList<Song> songArrayList;
    int position;
    public boolean isShuffle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        songNameNow = findViewById(R.id.song_name);
        artistNameNow = findViewById(R.id.artist_name);
        fastForward = findViewById(R.id.fast_forward);
        play = findViewById(R.id.play);
        fastRewind = findViewById(R.id.fast_rewind);
        repeat = findViewById(R.id.repeat);
        skipNext = findViewById(R.id.skip_next);
        previous = findViewById(R.id.skip_previous);
        shuffle = findViewById(R.id.shuffle);

        Intent intent = getIntent();
        songArrayList = intent.getParcelableArrayListExtra("list");
        String songName = intent.getStringExtra("song");
        String artistName = intent.getStringExtra("artist");
        position = intent.getIntExtra("position", 0);

        if (songArrayList == null) {
            return;
        } else {
            for (int x = 0; x < songArrayList.size(); x++) {
                Song song = songArrayList.get(x);
                Log.v(TAG, "onCreate: Song Name: " + song.getName() + " & Position: " + position);
            }
        }

        songNameNow.setText(songName);
        artistNameNow.setText(artistName);

        fastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.fast_forward_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.play_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        fastRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.fast_rewind_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.repeat_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        skipNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.skip_next_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NowPlaying.this, R.string.previous_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShuffle) {
                    isShuffle = true;
                    Toast.makeText(NowPlaying.this, R.string.shuffle_on_clicked, Toast.LENGTH_SHORT).show();
                } else {
                    isShuffle = false;
                    Toast.makeText(NowPlaying.this, R.string.shuffle_off_clicked, Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Setting clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homeAsUp:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.queue_music:
                Toast.makeText(this, "Queue Music clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, QueueMusic.class);
                i.putParcelableArrayListExtra("list", songArrayList);
                i.putExtra("shuffle", isShuffle);
                i.putExtra("position", position);
                startActivity(i);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
