package com.nakulbhoria.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);



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
        songArrayList = intent.getParcelableArrayListExtra("song");
        String songName = intent.getStringExtra("song");
        String artistName = intent.getStringExtra("artist");

        for (int x =0; x <songArrayList.size();x++){
            Song song = songArrayList.get(x);
            Log.d(TAG, "onCreate: Song Name: " + song.getName() );
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
                Toast.makeText(NowPlaying.this, R.string.shuffle_clicked, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
