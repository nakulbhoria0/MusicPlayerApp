package com.nakulbhoria.musicplayerapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 0;
    public ArrayList<Song> songList;
    private ListView songView;
    private SongAdapter songAdapter;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSongList();
            } else {
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songView = findViewById(R.id.song_list);
        songList = new ArrayList<>();

        songAdapter = new SongAdapter(this, songList);
        songView.setAdapter(songAdapter);

        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songList.get(position);
                String songName = song.getName();
                String artistName = song.getArtist();

                Intent i = new Intent(MainActivity.this, NowPlaying.class);
                i.putExtra("song",songList );
                i.putExtra("song", songName);
                i.putExtra("artist", artistName);
                startActivity(i);
            }
        });

        checkPermission();
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            getSongList();
        }
    }

    public void getSongList() {
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
            int titleCoulmn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idCoulmn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistCoulmn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            do {
                long thisId = musicCursor.getLong(idCoulmn);
                String thisTitle = musicCursor.getString(titleCoulmn);
                String thisArtist = musicCursor.getString(artistCoulmn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            } while (musicCursor.moveToNext());


            Collections.sort(songList, new Comparator<Song>() {
                @Override
                public int compare(Song a, Song b) {
                    return a.getName().compareTo(b.getName());
                }
            });
            songAdapter.notifyDataSetChanged();
        }
    }
}