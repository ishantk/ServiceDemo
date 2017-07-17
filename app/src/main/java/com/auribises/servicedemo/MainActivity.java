package com.auribises.servicedemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initList(){
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path);
        String[] fileNames = file.list();
        for(String str : fileNames){

            if(str.endsWith(".mp3") || str.endsWith(".wma"))
                adapter.add(str);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String songName = adapter.getItem(i);
        Intent intent = new Intent(MainActivity.this,PlayMusicActivity.class);
        intent.putExtra("keySong",songName);
        startActivity(intent);
    }
}
