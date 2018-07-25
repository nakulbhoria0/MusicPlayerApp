package com.nakulbhoria.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class extraActivity extends AppCompatActivity {


    ArrayList<Song> mSongList = new ArrayList<>();
    ArrayList<Song> songList;
    private ListView songView;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        songList = intent.getParcelableArrayListExtra("list");
        int positionNow = intent.getIntExtra("position", 0);
        songView = findViewById(R.id.song_list);

        for (int x = positionNow; x<songList.size();x++){
            Song song = songList.get(x);
            mSongList.add(song);
        }

        songAdapter = new SongAdapter(this, mSongList);
        songView.setAdapter(songAdapter);

        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songList.get(position);
                String songName = song.getName();
                String artistName = song.getArtist();

                Intent i = new Intent(extraActivity.this, NowPlaying.class);
                i.putParcelableArrayListExtra("list", songList );
                i.putExtra("song", songName);
                i.putExtra("artist", artistName);
                startActivity(i);
            }
        });



    }
}
