package com.auribises.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSong;
    Button btnPlay,btnStop;

    String songName;

    void initViews(){

        txtSong = (TextView)findViewById(R.id.textViewSong);
        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        Intent rcv = getIntent();
        songName = rcv.getStringExtra("keySong");

        txtSong.setText(songName);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initViews();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.buttonPlay){
            Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
            intent.putExtra("keySong",songName);
            startService(intent);
        }else{
            Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
            stopService(intent);
        }
    }
}
