package com.example.oneforall.ui.library;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneforall.R;

import java.io.File;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity{

    private Button btnPlay, btnRevs, btnFwrd;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;
    private TextView songTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        //init
        btnPlay = findViewById(R.id.btnPlay);
        btnRevs = findViewById(R.id.btnRevs);
        btnFwrd = findViewById(R.id.btnFwrd);
        songTitle = findViewById(R.id.txtTitle);

        Bundle bundle = getIntent().getExtras();

        ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
        int position = bundle.getInt("position");

        Uri uri = Uri.parse(songs.get(position).toString());
        int pos = uri.toString().lastIndexOf('/');
        String[] song_title =  {uri.toString().substring(0, pos+1), uri.toString().substring(pos)};
        songTitle.setText(song_title[1].split("/")[1]);
        mediaPlayer = MediaPlayer.create(this, uri);


//        btnPlay.setOnClickListener(this);
//        btnRevs.setOnClickListener(this);
//        btnFwrd.setOnClickListener(this);

        seekBar = findViewById(R.id.musicSeekBar);
        handler = new Handler();

//        mediaPlayer = MediaPlayer.create(this, R.raw.no_excuses);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                updateSeekBar();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                        updateSeekBar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    public void playMusic(View v){
        if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setText("Play");
                }else{
                    mediaPlayer.start();
                    btnPlay.setText("Pause");
                    updateSeekBar();
                }
    }

    public void fwdMusic(View v){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
    }

    public void revMusic(View v){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
    }
/*
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnPlay:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setText("Play");
                }else{
                    mediaPlayer.start();
                    btnPlay.setText("Pause");
                    updateSeekBar();
                }
                break;
            case R.id.btnFwrd:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                break;
            case R.id.btnRevs:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                break;
        }
    }
    */
}