package com.auribises.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.io.IOException;

public class MyMusicService extends Service {

    String songName;

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"--Service Created--",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        songName = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+songName;

        Toast.makeText(this,"--Service Started-- "+songName,Toast.LENGTH_LONG).show();

        String url = "http://www.auribises.com/sessions/Maroon5Sugar.mp3";

        player = new MediaPlayer();

        // VideoView - Explore

        try {

            player.setDataSource(this, Uri.parse(url));

            //player.setDataSource(path);
            player.prepare();
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //return Service.START_STICKY;
        //return Service.START_NOT_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"--Service Destroyed--",Toast.LENGTH_LONG).show();

        player.stop();
        player.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
