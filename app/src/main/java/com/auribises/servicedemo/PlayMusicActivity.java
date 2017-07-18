package com.auribises.servicedemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSong;
    Button btnPlay,btnStop;

    String songName;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    void initViews(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtSong = (TextView)findViewById(R.id.textViewSong);
        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        Intent rcv = getIntent();
        songName = rcv.getStringExtra("keySong");

        txtSong.setText(songName);

        preferences = getSharedPreferences("music",MODE_PRIVATE);
        editor = preferences.edit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initViews();
    }

    void showAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Play Song");
        builder.setMessage("Do you like to play the song "+songName+" ? ");
        builder.setCancelable(false); // Dialog will not be dismissed if user presses back key
        builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
                intent.putExtra("keySong",songName);
                startService(intent);
                editor.putString("keySong",songName);
                editor.commit();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showAlertDialogWithItems(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setCancelable(false);
        String[] items = {"Play","Stop","Delete","Mark Fav"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
                        intent.putExtra("keySong",songName);
                        startService(intent);
                        editor.putString("keySong",songName);
                        editor.commit();
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    Dialog dialog;
    Button btnSubmit;

    void showCustomDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        //dialog.setCancelable(false);

        btnSubmit = (Button)dialog.findViewById(R.id.button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.buttonPlay){
            /*Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
            intent.putExtra("keySong",songName);
            startService(intent);

            editor.putString("keySong",songName);
            //editor.remove("keySong");
            editor.commit(); // Save the Data in XML File
            //editor.apply(); // Save the Data in XML File, but in a background Thread

            //preferences.contains("keySong");
            */

            //showAlertDialog();
            //showAlertDialogWithItems();
            showCustomDialog();

        }else{
            Intent intent = new Intent(PlayMusicActivity.this,MyMusicService.class);
            stopService(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackPressed() {
        //super.onBackPressed(); // Destroys Activity
    }*/
}
